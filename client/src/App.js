import styled from 'styled-components'
import GlobalStyle from '../src/styles/GlobalStyle'
import { Reset } from 'styled-reset'
import { Routes, Route, useLocation, Navigate } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import { Suspense, lazy } from 'react'

import ScrollToTop from './utils/ScrollToTop'

const Home = lazy(() => import('./pages/Home'))
const Place = lazy(() => import('./pages/Place'))
const Mypage = lazy(() => import('./pages/Mypage/Mypage'))
const MyInfo = lazy(() => import('./pages/Mypage/MyInfo'))
const Bookmark = lazy(() => import('./pages/Mypage/Bookmark'))
const PwdEditFormModal = lazy(() => import('./pages/Mypage/PwdEditFormModal'))
const AboutUs = lazy(() => import('./pages/AboutUs'))
const SignUp = lazy(() => import('./pages/SignUp'))
const SignIn = lazy(() => import('./pages/SignIn'))
const InfoModal = lazy(() => import('./pages/InfoModal/InfoModal'))
const AddPlaceModal = lazy(() => import('./pages/AddPlaceModal/AddPlaceModal'))
const NotFound = lazy(() => import('./pages/NotFound'))
const Loading = lazy(() => import('./components/Loading/Loading'))

const Main = styled.main`
  display: flex;
  width: 100%;
  height: 100vh;
  background-color: #17ac52; // CI컬러 테스트용
  box-sizing: border-box;
`

const StyleToastContainer = styled(ToastContainer)`
  position: fixed;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
`

function App() {
  // 장소 상세 모달 팝업을 위한 로직
  const location = useLocation()
  const bgLocation = location.state && location.state.bgLocation

  return (
    <section className="App">
      <Reset />
      <GlobalStyle />
      <ScrollToTop />
      <StyleToastContainer
        position="top-center"
        autoClose={500}
        closeOnClick
        hideProgressBar={false}
        pauseOnHover
        pauseOnFocusLoss={false}
        draggable={false}
      />
      <Suspense fallback={<Loading />}>
        <Main>
          <Routes location={bgLocation || location}>
            <Route path="/" element={<Home />} />
            <Route path="/place" element={<Place />} />
            <Route path="/mypage" element={<Mypage />}>
              <Route index element={<Navigate to="/mypage/bookmark" />} />
              <Route path="bookmark" element={<Bookmark />} />
              <Route path="myinfo" element={<MyInfo />} />
            </Route>
            <Route path="/signup" element={<SignUp />} />
            <Route path="/signin" element={<SignIn />} />
            <Route path="*" element={<NotFound />} />
          </Routes>

          {/* 장소 상세보기 모달 라우터 */}
          {bgLocation && (
            <Routes>
              <Route path="/:infoId" element={<InfoModal />} />
              <Route path="/addPlace" element={<AddPlaceModal />} />
            </Routes>
          )}
        </Main>
      </Suspense>
    </section>
  )
}

export default App

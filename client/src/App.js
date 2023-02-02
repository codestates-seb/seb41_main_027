import styled from 'styled-components'
import GlobalStyle from '../src/styles/GlobalStyle'
import { Reset } from 'styled-reset'
import { Routes, Route, useLocation } from 'react-router-dom'
import { Slide, ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import { Suspense, lazy } from 'react'

import ScrollToTop from './utils/ScrollToTop'

const Home = lazy(() => import('./pages/Home'))
const Place = lazy(() => import('./pages/Place'))
const PlaceInfo = lazy(() => import('./pages/PlaceInfo'))
const Mypage = lazy(() => import('./pages/Mypage/Mypage'))
const MyInfo = lazy(() => import('./pages/Mypage/MyInfo'))
const Bookmark = lazy(() => import('./pages/Mypage/Bookmark'))
const Like = lazy(() => import('./pages/Mypage/Like'))
const Admin = lazy(() => import('./pages/Mypage/Admin'))
const PwdEditFormModal = lazy(() => import('./pages/Mypage/PwdEditFormModal'))
const AboutUs = lazy(() => import('./pages/AboutUs/AboutUs'))
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

function App() {
  // 장소 상세 모달 팝업을 위한 로직
  const location = useLocation()
  const bgLocation = location.state && location.state.bgLocation

  return (
    <section className="App">
      <Reset />
      <GlobalStyle />
      <ScrollToTop />
      <ToastContainer
        position="bottom-center"
        autoClose={600}
        closeOnClick
        hideProgressBar
        pauseOnHover
        pauseOnFocusLoss={false}
        draggable={false}
        transition={Slide}
        limit={2}
      />
      <Suspense fallback={<Loading />}>
        <Main>
          <Routes location={bgLocation || location}>
            <Route path="/" element={<Home />} />
            <Route path="/place" element={<Place />} />
            <Route path="/mypage" element={<Mypage />}>
              <Route path="bookmark" element={<Bookmark />} />
              <Route path="like" element={<Like />} />
              <Route path="myinfo" element={<MyInfo />} />
              <Route path="admin" element={<Admin />} />
            </Route>
            <Route path="/aboutus" element={<AboutUs />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/signin" element={<SignIn />} />
            <Route path="/placeinfo" element={<PlaceInfo />}>
              <Route path=":placeId" element={<InfoModal />} />
            </Route>
            <Route path="*" element={<NotFound />} />
          </Routes>

          {/* 모달 중첩 라우터 */}
          {bgLocation && (
            <Routes>
              <Route path="/:infoId" element={<InfoModal />} />
              <Route path="/addPlace" element={<AddPlaceModal />} />
              <Route path="/mypage/myinfo/pwdedit" element={<PwdEditFormModal />} />
            </Routes>
          )}
        </Main>
      </Suspense>
    </section>
  )
}

export default App

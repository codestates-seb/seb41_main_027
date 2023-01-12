import styled from 'styled-components'
import GlobalStyle from '../src/styles/GlobalStyle'
import { Reset } from 'styled-reset'
import { Routes, Route } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import { Suspense, lazy } from 'react'

import ScrollToTop from './utils/ScrollToTop'
import useToastPopup from './hooks/useToastPopup'

const Loading = lazy(() => import('./components/Loading/Loading'))
const Home = lazy(() => import('./pages/Home'))
const Place = lazy(() => import('./pages/Place'))
const Mypage = lazy(() => import('./pages/Mypage'))
const AboutUs = lazy(() => import('./pages/AboutUs'))
const SignUp = lazy(() => import('./pages/SignUp'))
const SignIn = lazy(() => import('./pages/SignIn'))

const Main = styled.main`
  display: flex;
  width: 100%;
  height: 100vh;
  background-color: #17ac52; // CI컬러 테스트용
  box-sizing: border-box;
`

const StyleToastContainer = styled(ToastContainer)`
  z-index: 9500;
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
  useToastPopup()

  return (
    <section className="App">
      <Reset />
      <GlobalStyle />
      <ScrollToTop />
      <StyleToastContainer position="top-center" pauseOnFocusLoss draggable pauseOnHover />
      <Suspense fallback={<Loading />}>
        <Main>
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/place" element={<Place />} />
            <Route path="/mypage" element={<Mypage />} />
            <Route path="/aboutus" element={<AboutUs />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/signin" element={<SignIn />} />
          </Routes>
        </Main>
      </Suspense>
      {/* 주석 샘플 다쓰고 나중에 날릴게요🥹 */}
    </section>
  )
}

export default App

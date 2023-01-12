import styled from 'styled-components'
import GlobalStyle from '../src/styles/GlobalStyle'
import { Reset } from 'styled-reset'
import { Routes, Route } from 'react-router-dom'
import { ToastContainer, toast } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

import ScrollToTop from './utils/ScrollToTop'

import Home from './pages/Home'
import Place from './pages/Place'
import Mypage from './pages/Mypage'
import Aboutus from './pages/AboutUs'
import SignUp from './pages/SignUp'
import SignIn from './pages/SignIn'
import { useEffect } from 'react'

const Main = styled.main`
  display: flex;
  width: 100%;
  height: 100vh;
  background-color: #13c57c;
  box-sizing: border-box;
`

const StyleToastContainer = styled(ToastContainer)`
  z-index: 9500;
`

function App() {
  // 전역 상태 추가되면 나중에 뺄게요.
  useEffect(() => {
    toast('App reload', { autoClose: 500 })
  }, [])

  return (
    <section className="App">
      <Reset />
      <GlobalStyle />
      <ScrollToTop />
      <StyleToastContainer position="top-center" pauseOnFocusLoss draggable pauseOnHover />
      <Main>
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="/place" element={<Place />} />
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/aboutus" element={<Aboutus />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/signin" element={<SignIn />} />
        </Routes>
      </Main>
      {/* 주석 샘플 다쓰고 나중에 날릴게요🥹 */}
    </section>
  )
}

export default App

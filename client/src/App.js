import styled from 'styled-components'
import GlobalStyle from '../src/styles/GlobalStyle'
// import { faPagelines } from '@fortawesome/free-brands-svg-icons'
// import { faMap, faComment, faBug, faUser } from '@fortawesome/free-solid-svg-icons'

import Nav from './components/Nav/Nav'
import Map from './components/Map/Map'
import Header from './components/Header/Header'

const Main = styled.main`
  display: flex;
  width: 100%;
  height: 100vh;
  background-color: #13c57c;
  box-sizing: border-box;
`

const Wrapper = styled.section`
  width: 100%;
`

function App() {
  return (
    <section className="App">
      <reset />
      <GlobalStyle />
      <Main>
        <Nav />
        <Wrapper>
          <Header />
          <Map />
        </Wrapper>
      </Main>
      {/* 주석 샘플 다쓰고 나중에 날릴게요🥹 */}
    </section>
  )
}

export default App

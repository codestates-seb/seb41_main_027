import styled from 'styled-components'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'
import KakaoMap from '../components/Map/Map'

const Wrapper = styled.section`
  width: 100%;
`

const Home = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <KakaoMap />
      </Wrapper>
    </>
  )
}

export default Home

import styled from 'styled-components'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'
import Main from '../components/Map/Map'

const Wrapper = styled.section`
  width: 100%;
`

const Home = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Main />
      </Wrapper>
    </>
  )
}

export default Home

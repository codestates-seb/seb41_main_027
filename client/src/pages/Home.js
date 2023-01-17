import styled from 'styled-components'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'
import MainMap from '../components/Map/Map'

const Wrapper = styled.section`
  width: 100%;
  > div {
    padding: 16px;
  }
`
const Home = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <>
          <MainMap />
        </>
      </Wrapper>
    </>
  )
}

export default Home

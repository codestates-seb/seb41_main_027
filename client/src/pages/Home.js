import styled from 'styled-components'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'
import Map from '../components/Map/Map'

const Wrapper = styled.section`
  width: 100%;
`

const Home = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Map />
      </Wrapper>
    </>
  )
}

export default Home

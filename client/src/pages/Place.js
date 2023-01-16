import styled from 'styled-components'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'

const Wrapper = styled.section`
  width: 100%;
`

const Container = styled.section`
  z-index: 1000;
  overflow: scroll;
  height: calc(100% - 100px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;

  p {
    height: 150px;
  }
`

const Place = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>Place</Container>
      </Wrapper>
    </>
  )
}

export default Place

import styled from 'styled-components'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'

const Wrapper = styled.section`
  width: 100%;
`

const Container = styled.section`
  z-index: 1000;
  overflow: hidden;
  height: calc(100% - 100px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;
`

const SignUp = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>SignUp</Container>
      </Wrapper>
    </>
  )
}

export default SignUp

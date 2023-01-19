import styled from 'styled-components'

import Register from '../components/Sign/Register'
// import Register from '../components/Sign/RegisterDemo'

// import { Link } from 'react-router-dom'
import Logo from '../assets/LogoTypeSignature.svg'

const Container = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`

const SignUp = () => {
  return (
    <Container>
      <h1>
        <img src={Logo} className="Logo" alt="EcoGreenSeoul Logo TypeSignature" />
      </h1>
      <Register />
    </Container>
  )
}

export default SignUp

import styled from 'styled-components'
import { Link, Outlet, useLocation } from 'react-router-dom'

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

  div {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
  }
`

const Mypage = () => {
  const location = useLocation()

  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>
          <div>
            <Outlet />
            <Link to="/1" state={{ bgLocation: location }}>
              장소 1
            </Link>
            <Link to="/2" state={{ bgLocation: location }}>
              장소 2
            </Link>
            <Link to="/3" state={{ bgLocation: location }}>
              장소 3
            </Link>
          </div>
        </Container>
      </Wrapper>
    </>
  )
}

export default Mypage

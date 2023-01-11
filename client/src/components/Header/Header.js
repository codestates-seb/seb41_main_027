import styled from 'styled-components'

import { Link } from 'react-router-dom'

const HeaderW = styled.header`
  z-index: 500;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 32px;
  width: calc(100% - 64px);
  height: 100px;
  color: #fff;
  h1 {
    font-size: 30px;
    font-weight: 700;
  }

  ul {
    display: flex;
    font-weight: 400;
    font-size: 24px;
    li {
      margin-left: 32px;
    }
  }
`

const Header = () => {
  return (
    <HeaderW>
      <h1>에코그린서울 제로웨이스트숍 공유 지도</h1>
      <ul>
        <li>
          <Link to="/signin">로그인</Link>
        </li>
        <li>
          <Link to="/">로그아웃</Link>
        </li>
        <li>
          <Link to="/signup">회원가입</Link>
        </li>
      </ul>
    </HeaderW>
  )
}

export default Header

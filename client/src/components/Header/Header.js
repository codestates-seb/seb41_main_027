import styled from 'styled-components'
import { Link } from 'react-router-dom'
import axios from 'axios'

// import { API_LOGIN_ENDPOINT } from '../utils/const'
// import { customAxios } from '../utils/customAxios'
const HeaderW = styled.header`
  z-index: 400;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 32px;
  width: calc(100% - 64px);
  height: 88px;
  color: #fff;

  & > h1 {
    font-size: 28px;
    font-weight: 400;
    letter-spacing: -0.5px;
    span {
      font-weight: 600;
    }
  }

  & > ul {
    display: flex;
    font-weight: 400;
    font-size: 18px;

    & > li {
      margin-left: 32px;
    }
  }
`

const HandleSignOut = () => {
  // 로그인을 할 때 -> 액세스, 리프래쉬를 받음(서버에서 응답으로 보내는 헤더)
  // 클라이언트에서 요청 헤더에 담아야 할 것
  // 로그인 후 접근 권한이 필요한 요청 -> 액세스
  // 재발급 요청("/reissue")-> 리프래쉬
  // 로그아웃할 때("/auth/logout") -> 액세스, 리프래쉬
  // 보미님 테스트 진행하실 때 참고하시라고 보냅니다~
  // const accessToken = localStorage.getItem('accessToken')
  // const refresh = localStorage.getItem('refresh')
  // if(accessToken && refresh) {
  //   try
  //   {
  //   // const response = await customAxios.post(API_LOGIN_ENDPOINT, { username: email, password })
  //     axios .post("/auth/logout", {
  //       accessToken, refresh
  //     })
  //     .then(res => {
  //       // 로그인 상태 변경
  //       localStorage.clear()
  //       // 메뉴 상태 변경
  //     })
  //   }
  //   }
}

const Header = () => {
  return (
    <HeaderW>
      <h1>
        우리가 그린 서울, <span>에코그린 서울</span>
      </h1>
      <ul>
        <li>
          <Link to="/signin">로그인</Link>
        </li>
        <li>
          <Link to="/" onClick={HandleSignOut}>
            로그아웃
          </Link>
        </li>
        <li>
          <Link to="/signup">회원가입</Link>
        </li>
      </ul>
    </HeaderW>
  )
}

export default Header

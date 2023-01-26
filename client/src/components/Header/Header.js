import styled from 'styled-components'
import { Link } from 'react-router-dom'
import { useState } from 'react'

import { API_LOGOUT_ENDPOINT } from '../../utils/const'
import { toast } from 'react-toastify'
import { customAxios } from '../../utils/customAxios'
import { getLoginInfo } from '../../api/login'

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

const Header = () => {
  const [loginMemberId, setLoginMemberId] = useState(getLoginInfo().id)

  const HandleSignOut = async () => {
    /*
     * signin
     - localStorage.clear()
     - 로그인 성공 : setLoginInfo > callbackUrl redirect
  
    * 로그아웃
     = /auth/logout api
       성공: localStorage.clear()
  
    * 비로그인 페이지(token) > api > 401 > reissue(refreshToken) > 401 > signin
       - OK : setLoginInfo
    
  
     */
    // 로그인을 할 때 -> 액세스, 리프래쉬를 받음(서버에서 응답으로 보내는 헤더)
    // 클라이언트에서 요청 헤더에 담아야 할 것
    // 로그인 후 접근 & refresh)
    try {
      await customAxios.post(API_LOGOUT_ENDPOINT)
      // 로그인 상태 변경
      localStorage.clear()
      setLoginMemberId('')
      toast.success('로그아웃 되었습니다.')
      // 메뉴 상태 변경(todo)
    } catch (error) {
      //에러처리(todo)
      console.error(error.message)
      toast.error('로그아웃에 실패했습니다.')
    }
  }

  return (
    <HeaderW>
      <h1>
        우리가 그린 서울, <span>에코그린 서울</span>
      </h1>
      <ul>
        {!loginMemberId && (
          <li>
            <Link to="/signin">로그인</Link>
          </li>
        )}
        {loginMemberId && (
          <li>
            <Link to="/" onClick={HandleSignOut}>
              로그아웃
            </Link>
          </li>
        )}
        <li>
          <Link to="/signup">회원가입</Link>
        </li>
      </ul>
    </HeaderW>
  )
}

export default Header

import styled from 'styled-components'
import { Link, useLocation, useNavigate } from 'react-router-dom'

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
  const { id: loginMemberId, nickName } = getLoginInfo()

  const navigate = useNavigate()
  const location = useLocation()

  const HandleSignOut = async () => {
    try {
      await customAxios.post(API_LOGOUT_ENDPOINT)
      // 로그인 상태 변경
      localStorage.clear()
      toast.success('로그아웃 되었습니다.')
      navigate(location.pathname, { replace: true })
    } catch (error) {
      //에러처리
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
          <>
            <li>
              <Link to="/signin">로그인</Link>
            </li>
            <li>
              <Link to="/signup">회원가입</Link>
            </li>
          </>
        )}
        {loginMemberId && (
          <>
            <h1>{nickName} 님 환영합니다! 🥰</h1>
            <li>
              <Link to="/" onClick={HandleSignOut}>
                로그아웃
              </Link>
            </li>
          </>
        )}
      </ul>
    </HeaderW>
  )
}

export default Header

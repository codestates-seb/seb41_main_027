import styled from 'styled-components'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPagelines } from '@fortawesome/free-brands-svg-icons'
import { faMap, faComment, faBug, faUser } from '@fortawesome/free-solid-svg-icons'
import Logo from '../Nav/images/Logo.svg'

// li사이즈만 빼둠 Nav css 셋업 안된 상태 -> 추후 Acitve 스타일링 필요
// <a href="/" className={type.page === "home" ? "selected" : ""}>

const StyleFontAwesomeIcon = styled(FontAwesomeIcon)`
  font-size: 28px;
  color: #fff;
  :hover {
    color: #13c57c;
  }
`
const Wrapper = styled.nav`
  z-index: 500;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 94px !important;
  height: 100%;

  .logo {
    position: relative;
    left: 5px;
    top: 11px;
    width: 57px;
    height: 61px;
    margin-bottom: 80px;
  }
  ul {
    padding: 16px;
    li:not(:first-child) {
      margin-bottom: 16px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      width: 68px;
      height: 75px;
      color: rgba(255, 255, 255, 0.7);
      line-height: 140%;
      font-size: 14px;
      font-weight: 600;
      border-radius: 8px;
      span {
        margin-top: 4px;
        letter-spacing: -0.5px;
      }
    }
    li:hover {
      color: #13c57c;
      font-weight: 700;
      background-color: rgba(255, 255, 255, 0.99);
      box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
    }
  }
`

const Nav = () => {
  return (
    <Wrapper>
      <ul>
        <li>
          {/* <link to={''} className="logo"></link> */}
          <img src={Logo} className="logo" alt="EcoGreenSeoul Logo" />
        </li>
        <li>
          <StyleFontAwesomeIcon icon={faPagelines} />
          <span>친환경지도</span>
        </li>
        <li>
          <StyleFontAwesomeIcon icon={faUser} />
          <span>마이페이지</span>
        </li>
        <li>
          <StyleFontAwesomeIcon icon={faMap} />
          <span>서비스소개</span>
        </li>
        <li>
          <StyleFontAwesomeIcon icon={faComment} />
          <span>오픈채팅</span>
        </li>
        <li>
          <StyleFontAwesomeIcon icon={faBug} />
          <span>오류제보</span>
        </li>
      </ul>
    </Wrapper>
  )
}

export default Nav

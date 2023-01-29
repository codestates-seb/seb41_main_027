import styled from 'styled-components'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPagelines } from '@fortawesome/free-brands-svg-icons'
import { faMap, faComment, faBug, faUser } from '@fortawesome/free-solid-svg-icons'
import Logo from '../Nav/images/Logo.svg'
import { Link } from 'react-router-dom'
import ReportModal from '../../pages/Report/ReportModal'
import { useState } from 'react'

const Wrapper = styled.nav`
  z-index: 1000;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 88px !important;
  height: 100%;

  .logo {
    cursor: pointer;
    position: relative;
    left: 5px;
    top: 5px;
    width: 57px;
    height: 61px;
    margin-bottom: 80px;
  }
  ul {
    padding: 16px;
    li:not(:first-child) {
      cursor: pointer;
      margin-bottom: 16px;
      a,
      button {
        width: 68px;
        height: 75px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        color: rgba(255, 255, 255, 1);
        /* color: rgba(255, 255, 255, 0.7); */
        font-size: 14px;
        line-height: 140%;
        font-weight: 600;
        border-radius: 16px;
        span {
          margin-top: 8px;
          letter-spacing: -0.5px;
        }
      }
      a:hover:not(.logo),
      button:hover:not(.logo) {
        color: #13c57c;
        font-weight: 700;
        background-color: rgba(255, 255, 255, 0.99);
        box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
      }
    }
  }
`

const Nav = () => {
  // 오류 제보 모달 팝업
  const [reportModalOpen, setReportModalOpen] = useState(false)

  return (
    <Wrapper>
      {reportModalOpen && <ReportModal modalClose={() => setReportModalOpen(false)} />}
      <ul>
        <li className="logo">
          <Link to="/" className="logo">
            <img src={Logo} alt="EcoGreenSeoul Logo" />
          </Link>
        </li>
        <li>
          <Link to="/">
            <FontAwesomeIcon icon={faMap} size="2x" />
            <span>친환경지도</span>
          </Link>
        </li>
        <li>
          <Link to="/mypage">
            <FontAwesomeIcon icon={faUser} size="2x" />
            <span>마이페이지</span>
          </Link>
        </li>
        <li>
          <Link to="/Aboutus">
            <FontAwesomeIcon icon={faPagelines} size="2x" />
            <span>서비스소개</span>
          </Link>
        </li>
        <li>
          <button onClick={() => window.open('https://open.kakao.com/o/g8FLpt1e', '_blank')}>
            <FontAwesomeIcon icon={faComment} size="2x" />
            <span>오픈채팅</span>
          </button>
        </li>
        <li>
          <button onClick={() => setReportModalOpen(true)}>
            <FontAwesomeIcon icon={faBug} size="2x" />
            <span>오류제보</span>
          </button>
        </li>
      </ul>
    </Wrapper>
  )
}

export default Nav

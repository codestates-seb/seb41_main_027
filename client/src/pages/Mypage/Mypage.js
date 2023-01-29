import styled, { css } from 'styled-components'
import { useState } from 'react'
import { useNavigate, Outlet, useLocation } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart, faBookmark } from '@fortawesome/free-solid-svg-icons'

import Nav from '../../components/Nav/Nav'
import Header from '../../components/Header/Header'
import useLoginCheck from '../../hooks/useLoginCheck'

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

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  gap: 30px;
`

const TabMenu = styled.ul`
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 16px;
  font-size: 1.5rem;
  margin-top: 20px;
`

const Menu = styled.li`
  padding: 12px 20px;
  background-color: #fff;
  cursor: pointer;
  ${props =>
    props.selected &&
    css`
      border-bottom: 6px solid var(--ecogreen-01);
    `}
`

const FontAwesomeIconStyle = styled(FontAwesomeIcon)`
  color: ${props => props.iconColor};
  font-size: 18px;
  vertical-align: top;
`

const Mypage = () => {
  useLoginCheck('redirect')

  // state, hook
  const navigate = useNavigate()
  const { pathname } = useLocation()

  const menuName = pathname.split('/')[2]

  // handle
  const handleClickTab = tabName => {
    navigate('/mypage/' + tabName)
  }

  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>
          <TabMenu>
            <Menu
              aria-label="북마크 탭 선택"
              selected={menuName === 'bookmark'}
              onClick={() => handleClickTab('bookmark')}
            >
              북마크&nbsp;
              <FontAwesomeIconStyle icon={faBookmark} iconColor="green" />
            </Menu>
            <Menu aria-label="나의정보 탭 선택" selected={menuName === 'like'} onClick={() => handleClickTab('like')}>
              추천장소&nbsp;
              <FontAwesomeIconStyle icon={faHeart} iconColor="red" />
            </Menu>
            <Menu
              aria-label="나의정보 탭 선택"
              selected={menuName === 'myinfo'}
              onClick={() => handleClickTab('myinfo')}
            >
              나의정보
            </Menu>
          </TabMenu>
          <Outlet />
        </Container>
      </Wrapper>
    </>
  )
}

export default Mypage

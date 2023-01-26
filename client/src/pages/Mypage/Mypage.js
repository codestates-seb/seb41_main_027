import styled, { css } from 'styled-components'
import { useState } from 'react'
import { useNavigate, Outlet, useLocation } from 'react-router-dom'

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

const Mypage = () => {
  useLoginCheck('redirect')

  // state, hook
  const navigate = useNavigate()
  const location = useLocation()
  const [selectedTab, setSelectedTab] = useState('북마크')

  // handle
  const handleClickTab = e => {
    const tabName = e.target.innerText
    setSelectedTab(tabName)
    if (tabName === '북마크') navigate('/mypage/bookmark')
    if (tabName === '나의정보') navigate('/mypage/myinfo')
  }

  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>
          <TabMenu>
            <Menu aria-label="북마크 탭 선택" selected={selectedTab === '북마크'} onClick={handleClickTab}>
              북마크
            </Menu>
            <Menu aria-label="나의정보 탭 선택" selected={selectedTab === '나의정보'} onClick={handleClickTab}>
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

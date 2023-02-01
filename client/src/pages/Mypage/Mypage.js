import styled, { css } from 'styled-components'
import { useNavigate, Outlet, useLocation } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

import Nav from '../../components/Nav/Nav'
import Header from '../../components/Header/Header'
import useLoginCheck from '../../hooks/useLoginCheck'

const Wrapper = styled.section`
  width: 100%;
`

const Container = styled.section`
  z-index: 1000;
  overflow: hidden;
  height: calc(100% - 88px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;
  padding: 40px;
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
  gap: 40px;
  font-size: 1.2rem;
  letter-spacing: -0.8px;
`

const Menu = styled.li`
  padding: 8px 4px;
  font-weight: 400;
  color: rgba(39, 39, 39, 0.7);
  border-bottom: 4px solid #fff;
  cursor: pointer;
  ${props =>
    props.selected &&
    css`
      color: #2c2d2e;
      font-weight: 500;
      border-bottom: 4px solid #2c2d2e;
      /* border-bottom: 4px solid var(--ecogreen-01); */
    `}
`

const FontAwesomeIconStyle = styled(FontAwesomeIcon)`
  color: ${props => props.color};
  font-size: 18px;
  vertical-align: top;
`

const Mypage = () => {
  useLoginCheck('redirect')

  // state, hook..
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
              북마크 리스트
            </Menu>
            <Menu aria-label="나의정보 탭 선택" selected={menuName === 'like'} onClick={() => handleClickTab('like')}>
              내가 좋아요한 장소
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

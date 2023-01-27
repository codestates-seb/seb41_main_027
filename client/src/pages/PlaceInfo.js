import styled from 'styled-components'
import { Outlet } from 'react-router-dom'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'

const Wrapper = styled.section`
  width: 100%;
`

const PlaceInfo = () => {
  // 카카오톡 공유하기 - 장소 상세보기 모달 페이지 바탕 화면
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Outlet />
      </Wrapper>
    </>
  )
}

export default PlaceInfo

import styled from 'styled-components'

import { API_MEMBER_ENDPOINT } from '../api/Member'
import useFetch from '../hooks/useFetch'
import toastPopupState from '../store/state/toastPopupState'
import { useRecoilState } from 'recoil'

import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'

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
`

const Mypage = () => {
  const [data, isLoading, error] = useFetch(API_MEMBER_ENDPOINT + '/1')
  const [toastPopup, setToastPopup] = useRecoilState(toastPopupState)

  const handleClick = () => {
    console.log('mypage toast', toastPopup)
    setToastPopup({ type: 'success', msg: 'OK!' })
  }

  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>
          MyPage
          <button onClick={handleClick}>토스트 팝업</button>
        </Container>
      </Wrapper>
    </>
  )
}

export default Mypage

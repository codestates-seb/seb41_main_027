import styled from 'styled-components'
import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'
import MainMap from '../components/Map/MainMap'
import { useRecoilValue } from 'recoil'
import { placeSort } from '../recoil/atoms'

const Wrapper = styled.section`
  width: 100%;
  > div {
    padding: 16px;
  }
`
const Home = () => {
  const sort = useRecoilValue(placeSort)
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <>
          <MainMap sort={sort} />
        </>
      </Wrapper>
    </>
  )
}

export default Home

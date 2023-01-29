import styled from 'styled-components'
import Nav from '../components/Nav/Nav'
import Header from '../components/Header/Header'
import MainMap from '../components/Map/MainMap'
import { useRecoilValue } from 'recoil'
import { categoryId, placeSort } from '../recoil/atoms'

const Wrapper = styled.section`
  width: 100%;
`
const Home = () => {
  const sort = useRecoilValue(placeSort)
  const category = useRecoilValue(categoryId)
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <>
          <MainMap sort={sort} categoryId={category} />
        </>
      </Wrapper>
    </>
  )
}

export default Home

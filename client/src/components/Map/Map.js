import styled from 'styled-components'

import SearchBar from './SearchBar/SearchBar'

const Container = styled.section`
  z-index: 1000;
  overflow: hidden;
  padding: 32px; // Demo Position 🫡
  box-sizing: border-box;
  height: calc(100% - 94px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;
`

const Map = () => {
  return (
    <Container>
      <SearchBar />
    </Container>
  )
}

export default Map

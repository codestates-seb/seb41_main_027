import styled from 'styled-components'

const Container = styled.section`
  z-index: 1000;
  overflow: hidden;
  height: calc(100% - 100px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;
`

const Map = () => {
  return <Container>🫡 Test 🫡</Container>
}

export default Map

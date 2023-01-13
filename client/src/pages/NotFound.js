import styled from 'styled-components'

const Wrapper = styled.section`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 3rem;
`

const NotFound = () => {
  return <Wrapper>404 Not Found</Wrapper>
}

export default NotFound

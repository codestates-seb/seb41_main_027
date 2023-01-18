import styled from 'styled-components'

const Wrapper = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;

  h1 {
    font-size: 3rem;
    font-weight: bold;
    margin-bottom: 40px;
  }

  p {
    font-size: 1.5rem;
    margin-bottom: 20px;
  }
`

const NotFound = () => {
  return (
    <Wrapper>
      <h1>404 Not Found</h1>
      <p>This is not the web page you are looking for.</p>
    </Wrapper>
  )
}

export default NotFound

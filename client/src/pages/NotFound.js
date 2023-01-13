import styled from 'styled-components'

const Wrapper = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;

  p {
    font-size: 3rem;
    margin-bottom: 20px;
  }
`

const NotFound = () => {
  return (
    <Wrapper>
      <p>Welcome To Eco Green Seoul</p>
      <div>comming soon</div>
    </Wrapper>
  )
}

export default NotFound

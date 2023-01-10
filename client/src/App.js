import GlobalStyle from '../src/styles/GlobalStyle'
import styled from 'styled-components'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPagelines } from '@fortawesome/free-brands-svg-icons'

const StyleFontAwesomeIcon = styled(FontAwesomeIcon)`
  color: green;
`

function App() {
  return (
    <section className="App">
      <reset />
      <GlobalStyle />
      <h1 className="test">
        <StyleFontAwesomeIcon icon={faPagelines} />
        Set up 🫡
      </h1>

      {/* 주석 샘플 나중에 다쓰고 날릴게요🥹 */}
    </section>
  )
}

export default App

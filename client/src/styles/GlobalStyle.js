// global style 적용
import { createGlobalStyle } from 'styled-components'
import reset from 'styled-reset'

// 글로벌 스타일 만들기, reset 사용해서 스타일 초기화하기
const GlobalStyle = createGlobalStyle`
  ${reset}
  /* 필요한 부분 추가해주셔요 🥹 */
  
  body{
    margin: 0;
    padding: 0;
    min-height: 100vh;
    background-color: #ffffff;
    color: #232629;
    font-family: "Roboto",system-ui,BlinkMacSystemFont,"Segoe UI","Helvetica Neue",Arial,sans-serif; 
  }

  a {
    text-decoration: none;
    outline: none;
    font-size: 100%;
    color: inherit;
  }

  input, button {
    background-color: transparent;
    border: none;
    outline: none;
  }

  h1, h2, h3, h4, h5, h6{
    font-weight: normal;
    margin: 0;
    font-size: 100%;
  }

  ul {
    margin: 0;
    padding: 0;
  }

  ol, ul, li {
    list-style: none;
  }

  img {
    display: block;
    width: 100%;
    height: 100%;
  }

  button {
    padding: 0;
    background-color: transparent;
    font-size: 100%;
    border: none;
    cursor: pointer;
  }


`

export default GlobalStyle

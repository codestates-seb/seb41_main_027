// global style 적용
import { createGlobalStyle } from 'styled-components'
import { reset } from 'styled-reset'

// 글로벌 스타일 만들기, reset 사용해서 스타일 초기화하기
const GlobalStyle = createGlobalStyle`
  ${reset}
  /* 필요한 부분 추가해주셔요 🥹 */
  * {
    box-sizing: border-box;
    font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
  }
  
  html, body{
    margin: 0;
    padding: 0;
    min-height: 100vh;
    background-color: #ffffff;
    color: #232629;
  }

  a {
    text-decoration: none;
    outline: none;
    font-size: 100%;
    color: inherit;
  }

  input, button, textarea {
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

  // animation
  @keyframes fadeout {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }
  @-moz-keyframes fadeout {
    /* Firefox */
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }
  @-webkit-keyframes fadeout {
    /* Safari and Chrome */
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }
  @-o-keyframes fadeout {
    /* Opera */
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }

  @keyframes fadein {
    0% {
      opacity: 0.5;
    }
    100% {
      opacity: 1;
    }
  }
  @-moz-keyframes fadein {
    /* Firefox */
    0% {
      opacity: 0.5;
    }
    100% {
      opacity: 1;
    }
  }
  @-webkit-keyframes fadein {
    /* Safari and Chrome */
    0% {
      opacity: 0.5;
    }
    100% {
      opacity: 1;
    }
  }
  @-o-keyframes fadein {
    /* Opera */
    0% {
      opacity: 0.5;
    }
    100% {
      opacity: 1;
    }
  }

  // root
  :root { 
    // 💄 CI set 
    --ecogreen-01: #17AC52;
    /* --ecogreen-02: #31B679;
    --ecogreen-03: #13C57C;
    --ecored-01:#FF3838;
    --ecored-02:#FF1F1F; */
    --border-01: #B8BCCF;
    /* --border-02: #D9D9D9; */

    // input focus
    --border-input-focus: 1px solid #76B5F2;
    --outline-input-focus: 4px solid #D8E4F1;
    --outline-btn-press: 4px solid #E0EAF6;

    // box shadow
    --box-shadow-base: 0px 4px 10px rgba(25, 1, 52, 0.4);
    --box-shadow-list: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
    --box-shadow-item: 1px 1px 2px rgb(0 0 0 / 10%);
  }
`

export default GlobalStyle

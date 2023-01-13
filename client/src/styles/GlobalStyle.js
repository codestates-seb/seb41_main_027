// global style 적용
import { createGlobalStyle } from 'styled-components'
import { reset } from 'styled-reset'

// 글로벌 스타일 만들기, reset 사용해서 스타일 초기화하기
const GlobalStyle = createGlobalStyle`
  ${reset}
  /* 필요한 부분 추가해주셔요 🥹 */
  
  html, body{
    margin: 0;
    padding: 0;
    min-height: 100vh;
    background-color: #ffffff;
    color: #232629;
    font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;  }

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

  // 💄 CI set 
  --ecogreen-01: #17AC52;
  --ecogreen-02: #31B679;
  --ecogreen-03: #13C57C;
  --ecored-01:#FF3838;
  --ecored-02:#FF1F1F;
  --border-01: #B8BCCF;
  --border-02: #D9D9D9; 

  //----------------- toastify css start -----------------
  :root {  
  /* 테마별 색상 바꾸기 */
  --toastify-color-light: #fff;
  --toastify-color-dark: #121212;
  --toastify-color-info: #3498db;
  --toastify-color-success: #07bc0c;
  --toastify-color-warning: #f1c40f;
  --toastify-color-error: #e74c3c;
  --toastify-color-transparent: rgba(255, 255, 255, 0.7);

  /* 테마별 아이콘 색상 바꾸기 */
  --toastify-icon-color-info: var(--toastify-color-info);
  --toastify-icon-color-success: var(--toastify-color-success);
  --toastify-icon-color-warning: var(--toastify-color-warning);
  --toastify-icon-color-error: var(--toastify-color-error);

  /* 기본 적용 스타일 바꾸기 */
  --toastify-toast-width: 320px;
  --toastify-toast-background: #fff;
  --toastify-toast-min-height: 100px; // 64px
  --toastify-toast-max-height: 800px;
  --toastify-font-family: sans-serif;
  --toastify-z-index: 9500;
  --toastify-text-color-light: #757575;
  --toastify-text-color-dark: #fff;

  /* Used only for colored theme */
  --toastify-text-color-info: #fff;
  --toastify-text-color-success: #fff;
  --toastify-text-color-warning: #fff;
  --toastify-text-color-error: #fff;
  --toastify-spinner-color: #616161;
  --toastify-spinner-color-empty-area: #e0e0e0;

  /* Used when no type is provided */
  /* toast("**hello**") */
  --toastify-color-progress-light: linear-gradient(
    to right,
    #4cd964,
    #5ac8fa,
    #007aff,
    #34aadc,
    #5856d6,
    #ff2d55
  );

  /* Used when no type is provided */
  --toastify-color-progress-dark: #bb86fc;
  --toastify-color-progress-info: var(--toastify-color-info);
  --toastify-color-progress-success: var(--toastify-color-success);
  --toastify-color-progress-warning: var(--toastify-color-warning);
  --toastify-color-progress-error: var(--toastify-color-error);
  //----------------- toastify css end -----------------
  }
`

export default GlobalStyle

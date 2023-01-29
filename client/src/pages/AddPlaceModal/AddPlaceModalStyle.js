import styled from 'styled-components'

export const AddPlaceModalWrapper = styled.div`
  width: 370px;
  height: 450px;
  position: relative;
  z-index: 9200;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  color: #666666;
  background-color: #f4f4f4;
  font-size: 1rem;
  box-shadow: 0px 4px 10px rgba(25, 1, 52, 0.4);
  border-radius: 16px;
  overflow: hidden;

  @media (max-width: 750px) {
    width: calc(100vw - 10%);
    height: calc(100vh - 10%);
  }

  @media (max-width: 640px) and (max-height: 520px) {
    width: calc(100vw - 5%);
    height: calc(100vh - 40px);
  }

  // common style
  & button:hover {
    filter: brightness(0.85);
  }

  & textarea {
    width: calc(100% - 4px);
    min-height: 80px;
    color: #666666;
    background-color: #e8e8e8;
    border-radius: 12px;
    padding: 18px;
    line-height: 20px;
    resize: none;
    font-size: 1rem;
    box-shadow: var(--box-shadow-list);

    &:focus {
      &:not(:read-only) {
        border: var(--border-input-focus);
        outline: var(--outline-input-focus);
      }
    }
  }

  & h3 {
    color: #545454;
    font-weight: 500;
    height: 30px;
  }

  & .ani-fadeout {
    animation: fadeout 2s;
    -moz-animation: fadeout 2s; /* Firefox */
    -webkit-animation: fadeout 2s; /* Safari and Chrome */
    -o-animation: fadeout 2s; /* Opera */
    animation-fill-mode: both;
  }

  & .ani-fadein {
    animation: fadein 1s;
    -moz-animation: fadein 1s; /* Firefox */
    -webkit-animation: fadein 1s; /* Safari and Chrome */
    -o-animation: fadein 1s; /* Opera */
    animation-fill-mode: both;
  }

  & .ani-border-twinkle {
    animation: border-twinkle 1.5s;
    -moz-animation: border-twinkle 1s; /* Firefox */
    -webkit-animation: border-twinkle 1s; /* Safari and Chrome */
    -o-animation: border-twinkle 1s; /* Opera */
    animation-fill-mode: both;
  }
`

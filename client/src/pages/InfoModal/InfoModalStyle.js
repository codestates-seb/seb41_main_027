import styled, { css } from 'styled-components'

export const ModalDimmed = styled.div`
  position: fixed;
  z-index: 9100;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  background: rgba(0, 0, 0, 0.6);
  overflow-y: auto;
`

export const ModalWrapper = styled.div`
  z-index: 9200;
  width: 750px;
  height: calc(100vh - 18%);
  max-height: 720px;

  position: relative;
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

  /* animation */
  animation: slide-up 0.3s;
  -moz-animation: slide-up 0.3s;
  -webkit-animation: slide-up 0.3s;
  -o-animation: slide-up 0.3s;

  animation-fill-mode: forwards;

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

export const InfoContent = styled.div`
  height: 100%;
  padding: 32px;
`

export const InfoHead = styled.div`
  width: 100%;
  margin-bottom: 24px;
`

export const InfoBody = styled.div`
  width: 100%;
  min-height: 200px;
  max-height: calc(100% - 190px + 8px);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 12px;
  padding-bottom: 20px;

  // scroll bar hidden
  overflow-y: auto;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
`

export const InfoMapReview = styled.div`
  width: 100%;
  display: flex;
  /* align-items: flex-start; */
  gap: 24px;

  @media (max-width: 750px) {
    flex-direction: column;
  }
`

export const InfoReviewListWrap = styled.section`
  width: 100%;
  flex-grow: 1;
`

export const InfoMapReviewAdd = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex-grow: 1;
  gap: 40px;
`

export const InfoBottom = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 16px 32px 32px 32px;
  background-color: #f4f4f4;

  position: fixed;
  width: 100%;
  height: 90px;
  bottom: 0px;

  @media (max-height: 400px) {
    position: relative;
  }
`

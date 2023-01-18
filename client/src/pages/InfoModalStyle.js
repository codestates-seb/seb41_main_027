import styled from 'styled-components'

// export const ModalDimmed = styled.div.attrs(prps => ({
//   role: 'dimmed',
// }))`

export const ModalDimmed = styled.div`
  position: fixed;
  z-index: 9100;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  background: rgba(0, 0, 0, 0.6);
`

export const ModalWrapper = styled.div`
  width: 750px;
  height: calc(100vh - 18%) !important;
  position: absolute;
  z-index: 9200;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);

  color: #666666;
  font-size: 1rem;
  background-color: #f8f8f8;
  box-shadow: 0px 4px 10px rgba(25, 1, 52, 0.4);
  border-radius: 16px;
  overflow: hidden;

  // common style
  & button:hover {
    filter: brightness(0.85);
  }

  & textarea {
    width: calc(100% - 4px);
    min-height: 100px;
    color: #666666;
    background-color: #eee;
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
    height: 40px;
  }

  @media (max-width: 750px) {
    width: calc(100vw - 10%);
    height: calc(100vh - 5%);
  }

  @media (max-width: 640px) and (max-height: 520px) {
    width: calc(100vw - 5%);
    height: calc(100vh);
  }
`

export const InfoContent = styled.div`
  padding: 24px;
`

export const InfoHead = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  margin-bottom: 24px;

  .head-category-title {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    height: 38px;

    .head-title {
      display: flex;
      align-items: center;
      gap: 6px;

      .category {
        min-width: 50px;
        display: flex;
        align-items: center;
        padding: 8px;
        border-radius: 8px;
        color: #fff;
        font-size: 14px;
        background-color: var(--ecogreen-01);
      }

      & p {
        color: var(--ecogreen-01);
        font-size: 20px;
        font-weight: 600;
      }
    }

    & > button {
      font-size: 44px;
      font-weight: 200;
      color: #999;
      padding-bottom: 5px;
    }
  }

  .head-address {
    display: flex;
    align-items: center;
    padding: 0px 4px;
    width: 100%;
    height: 30px;
    gap: 10px;

    & > p,
    & svg {
      color: #666666;
      font-size: 0.9rem;
      letter-spacing: -0.6px;
    }

    .copied {
      position: relative;
      top: -28px;
      left: -50px;
      color: #fff;
      background-color: #888;
      border-radius: 0.4em;
      font-size: 0.9rem;
      padding: 4px 8px;
      animation: fadeout 2.5s;
      -moz-animation: fadeout 2.5s; /* Firefox */
      -webkit-animation: fadeout 2.5s; /* Safari and Chrome */
      -o-animation: fadeout 2.5s; /* Opera */
      animation-fill-mode: both;

      &:after {
        content: '';
        position: absolute;
        bottom: 0;
        left: 50%;
        border: 8px solid transparent;
        border-top-color: #888;
        border-bottom: 0;
        border-left: 4px;
        margin-bottom: -6px;
      }
    }
  }

  .head-upvote-report {
    display: flex;
    align-items: center;
    padding: 0px 4px;
    width: 100%;
    height: 30px;
    gap: 32px;

    .head-upvote {
      display: flex;
      align-items: center;
      color: #666666;
      gap: 8px;

      & svg {
        color: #ff0000;
      }
    }

    .head-report {
      display: flex;
      align-items: center;
      gap: 8px;

      & > button,
      & > svg {
        background-color: #f8f8f8;
        color: #666666;
      }
    }
  }
`

export const InfoBody = styled.div`
  width: 100%;
  min-height: 150px;
  max-height: 370px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 12px;

  // scroll bar hidden
  overflow-y: auto;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
`

export const InfoAbout = styled.div`
  width: 100%;

  .body-about-modify {
    text-align: right;
    padding: 2px;

    & button {
      color: skyblue;
      font-size: 0.9rem;
      text-decoration: underline;
    }
  }
`

export const InfoMapReview = styled.div`
  width: 100%;
  display: flex;
  align-items: flex-start;
  gap: 24px;

  @media (max-width: 640px) {
    flex-direction: column;
  }
`

export const InfoReviewList = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex-grow: 1;

  & ul {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;

    & > li {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: relative;
      padding: 16px;
      background-color: #fff;
      box-shadow: var(--box-shadow-list);
      border-radius: 8px;

      .review-comment {
        font-size: 14px;
        text-align: left;
        line-height: 20px;
        word-wrap: break-all;
        letter-spacing: -0.4px;
        flex-grow: 1;
      }

      .review-emoji {
        position: absolute;
        top: -8px;
        left: 8px;
        font-size: 20px;
      }

      .review-del-btn {
        color: #999;
        font-size: 20px;
        font-weight: 100;
        position: absolute;
        top: -4px;
        right: 4px;
      }
    }
  }
`

export const InfoMapReviewAdd = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex-grow: 1;
  gap: 40px;

  .map {
    width: calc(100% - 4px);

    .map-static {
      height: 160px;
      background-color: #eee;
      border-radius: 12px;
      box-shadow: var(--box-shadow-list);
      overflow: hidden;
    }
  }

  .review-add-form {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;

    .review-add-emoji-box {
      width: 100%;
      display: flex;
      flex-direction: column;

      .emoji-list {
        width: calc(100% - 4px);
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        margin-bottom: 10px;

        .emoji-item {
          width: 56px;
          height: 56px;

          & label {
            font-size: 24px;
            padding: 14px 16px;
            background-color: #fff;
            box-shadow: var(--box-shadow-list);
            border-radius: 8px;
          }

          & input[type='radio'] {
            width: 0;
            height: 0;
            opacity: 0;
          }

          & input[type='radio']:checked + label {
            background-color: var(--ecogreen-01);
            animation: fadein 1s;
            -moz-animation: fadein 1s; /* Firefox */
            -webkit-animation: fadein 1s; /* Safari and Chrome */
            -o-animation: fadein 1s; /* Opera */
            animation-fill-mode: both;
          }
        }
      }
    }

    .review-add-comment {
      width: 100%;
      display: flex;
      flex-direction: column;
      gap: 16px;

      .comment-save-btn {
        height: 50px;
        border-radius: 12px;
        background-color: #e5e5e5;

        &:disabled {
          cursor: not-allowed;
        }
      }
    }
  }
`

export const InfoBottom = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 16px 24px 24px 24px;
  background-color: #f8f8f8;

  position: absolute;
  width: 100%;
  height: 90px;
  bottom: 0px;

  @media (max-height: 400px) {
    position: relative;
  }

  .bottom-wrapper {
    display: flex;
    justify-content: center;
    align-items: flex-end;
    width: 100%;
    height: 100%;
    gap: 16px;

    & > div {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100%;
      gap: 10px;
    }

    & button {
      height: 100%;
      box-shadow: var(--box-shadow-base);
      border-radius: 8px;
    }

    .btn-icon,
    .kakao-share-btn {
      width: 50px;
      color: #999;
      font-size: 24px;
      background-color: #fff;
    }

    .bottom-detail-link {
      flex-grow: 1;

      & > button {
        width: 100%;
        color: #fff;
        background-color: var(--ecogreen-01);
      }
    }
  }
`

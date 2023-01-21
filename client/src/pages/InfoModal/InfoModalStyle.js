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
  overflow-y: auto;
`

export const ModalWrapper = styled.div`
  width: 750px;
  height: calc(100vh - 18%);
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

export const InfoContent = styled.div`
  height: 100%;
  padding: 32px;
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
    height: 40px;

    .head-title {
      display: flex;
      align-items: center;
      gap: 6px;

      .category {
        display: flex;
        align-items: center;
        padding: 8px 12px;
        border-radius: 8px;
        color: #fff;
        font-size: 16px;
        background-color: var(--ecogreen-01);
      }

      & p {
        color: var(--ecogreen-01);
        font-size: 24px;
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
  }

  .head-like-report {
    display: flex;
    align-items: center;
    padding: 0px 4px;
    width: 100%;
    height: 30px;
    gap: 32px;

    .head-like {
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

  .info-about-form {
    width: 100%;

    .body-about-modify {
      display: flex;
      justify-content: flex-end;
      margin-right: 10px;
      margin-top: 2px;
      gap: 12px;

      & button {
        color: #fff;
        font-size: 0.8rem;
        padding: 2px 4px;
        border-radius: 4px;
        background-color: #888;
      }

      & .cancel-btn {
        color: #888;
        border: 1px solid #888;
        background-color: transparent;
      }
    }
  }
`

export const InfoMapReview = styled.div`
  width: 100%;
  display: flex;
  align-items: flex-start;
  gap: 24px;

  @media (max-width: 750px) {
    flex-direction: column;
  }
`

export const InfoReviewList = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex-grow: 1;

  .review-cnt {
    color: var(--ecogreen-01);
  }

  .review-list {
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
        font-size: 24px;
      }

      .review-date {
        color: #999;
        font-size: 0.84rem;
        font-weight: 200;
        position: absolute;
        top: 4px;
        right: 24px;
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
  } // end list

  .list-pagination {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 30px;
    margin-top: 12px;
    gap: 10px;

    & button {
      padding: 2px 8px;
      border: 1px solid var(--ecogreen-01);
      border-radius: 4px;
      box-shadow: var(--box-shadow-item);
    }

    & .selected {
      color: #fff;
      background-color: var(--ecogreen-01);
    }
  }
`

export const InfoMapReviewAdd = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex-grow: 1;
  gap: 26px;

  .map {
    width: calc(100% - 4px);

    .map-static {
      height: 200px;
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
  background-color: #f4f4f4;

  position: fixed;
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

    .bookmark {
      color: var(--ecogreen-01);
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

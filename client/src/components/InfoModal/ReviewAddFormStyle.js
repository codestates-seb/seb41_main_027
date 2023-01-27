import styled, { css } from 'styled-components'

export const InfoReviewAddForm = styled.form`
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

    .login-comment {
      width: calc(100% - 2px);
      height: 100px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      gap: 14px;
      font-size: 0.9rem;
      background-color: #fff;
      box-shadow: var(--box-shadow-item);
      border-radius: 8px;

      & > button {
        color: #fff;
        background-color: var(--ecogreen-01);
        padding: 8px;
        border-radius: 8px;
      }
    }
  }
`

export const EmojiBox = styled.div`
  width: 56px;
  height: 56px;
  padding: 14px 16px;
  background-color: #fff;
  box-shadow: var(--box-shadow-list);
  border-radius: 8px;

  ${props =>
    props.selected &&
    css`
      background-color: var(--ecogreen-01);
      animation: fadein 1s;
      -moz-animation: fadein 1s; /* Firefox */
      -webkit-animation: fadein 1s; /* Safari and Chrome */
      -o-animation: fadein 1s; /* Opera */
      animation-fill-mode: both;
    `}
`

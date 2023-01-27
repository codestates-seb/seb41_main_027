import styled, { css } from 'styled-components'

export const InfoReviewList = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  flex-grow: 1;

  .review-cnt {
    color: var(--ecogreen-01);
  }
`

export const List = styled.ul`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 16px;
`

export const Item = styled.li`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  padding: 16px;
  background-color: #fff;
  box-shadow: var(--box-shadow-list);
  border-radius: 8px;

  ${props =>
    props.isModifyed &&
    css`
      animation: border-twinkle 1.8s;
      -moz-animation: border-twinkle 2s; /* Firefox */
      -webkit-animation: border-twinkle 2s; /* Safari and Chrome */
      -o-animation: border-twinkle 2s; /* Opera */
      animation-fill-mode: both;
    `}

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
    top: -12px;
    left: 12px;
    font-size: 24px;

    & > img {
      width: 22px;
      height: 22px;
    }
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
`

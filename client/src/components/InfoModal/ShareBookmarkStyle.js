import styled from 'styled-components'

export const InfoShareBookmark = styled.div`
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
`

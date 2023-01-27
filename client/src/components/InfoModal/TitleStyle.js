import styled from 'styled-components'

export const InfoTitle = styled.div`
  width: 100%;

  .head-category-title {
    display: flex;
    justify-content: space-between;
    align-items: center;
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
`

export const ModalCloseBtn = styled.button`
  font-size: 44px;
  font-weight: 200;
  color: #999;
  padding-bottom: 5px;
`

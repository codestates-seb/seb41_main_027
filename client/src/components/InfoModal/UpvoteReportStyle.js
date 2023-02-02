import styled from 'styled-components'

export const InfoUpvoteReport = styled.div`
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
      color: #666666;
    }
  }
`

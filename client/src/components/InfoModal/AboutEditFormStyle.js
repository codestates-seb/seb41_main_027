import styled from 'styled-components'

export const InfoAboutEditForm = styled.form`
  width: 100%;
  margin-bottom: 16px;

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
`

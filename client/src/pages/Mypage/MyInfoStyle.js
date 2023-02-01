import styled, { css } from 'styled-components'

export const ContentWrap = styled.div`
  width: 80%;
  max-width: 800px;
  min-height: calc(100% - 300px);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 50px 60px;
  gap: 20px;
  border-radius: 20px;
  background-color: #eee;
`

export const Item = styled.div`
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 8px;
  border-bottom: 1px solid var(--border-01);
`

export const FieldName = styled.div`
  width: 100px;
  font-size: 1.2rem;
  color: var(--ecogreen-01);
  font-weight: bold;
`

export const FieldText = styled.div`
  flex-grow: 1;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 10px;
`

export const ValidText = styled.span`
  font-size: ${props => (props.isValid ? '1rem' : '0.8rem')};
  color: ${props => (props.isValid ? 'green' : 'red')};
`

export const EditForm = styled.form`
  display: flex;
  gap: 4px;
`

export const Input = styled.input`
  width: 200px;
  height: 36px;
  padding: 8px;
  font-size: 1rem;
  border-radius: 8px;

  &:focus {
    &:not(:read-only) {
      background-color: #fff;
      border: var(--border-input-focus);
      outline: var(--outline-input-focus);
    }
  }
`

export const Button = styled.button`
  /* width: 84px; */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 32px;
  padding: 2px 16px;
  font-size: 18px;
  font-weight: 400;
  border-radius: 8px;
  box-sizing: border-box;
  ${props =>
    props.cancel
      ? css`
          border: 1px solid var(--ecogreen-01);
        `
      : css`
          color: #fff;
          background-color: var(--ecogreen-01);
        `}

  &:hover {
    filter: brightness(0.85);
  }
`

export const WithdrawalBtn = styled.button`
  position: relative;
  top: calc(100% - 190px);
  left: calc(100% - 80px);
  width: 84px;
  height: 40px;
  padding: 0px 12px;
  /* background-color: #ccc; */
  font-size: 16px;
  color: #535353;
  border-radius: 12px;
  box-sizing: border-box;
`

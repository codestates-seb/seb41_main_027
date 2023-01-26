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
  max-width: 400px;
  height: calc(100vh - 18%);
  max-height: 450px;
  position: relative;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  color: #666666;
  background-color: #f2f3f5;
  font-size: 1rem;
  box-shadow: 0px 4px 10px rgba(25, 1, 52, 0.4);
  border-radius: 16px;
`

export const EditForm = styled.form`
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 48px;
  gap: 36px;
`

export const Item = styled.div`
  width: 100%;
`

export const FieldName = styled.div`
  height: 30px;
  font-size: 1.2rem;
  color: var(--ecogreen-01);
  font-weight: bold;
`

export const Input = styled.input`
  width: 100%;
  height: 50px;
  padding: 8px;
  font-size: 0.9rem;
  border: 1px solid silver;
  border-radius: 8px;
  background-color: #fff;
  margin-bottom: 8px;

  &:focus {
    &:not(:read-only) {
      border: 2px solid ${props => (props.isValid ? 'green' : 'red')};
      outline: var(--outline-input-focus);
    }
  }
`

export const ValidText = styled.span`
  font-size: 0.8rem;
  color: ${props => (props.isValid ? 'green' : 'red')};
`

export const ButtonWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 24px;
`

export const Button = styled.button`
  width: 100%;
  height: 50px;
  padding: 0 12px;
  border-radius: 12px;
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

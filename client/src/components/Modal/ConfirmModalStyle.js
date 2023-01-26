import styled from 'styled-components'

export const ConfirmModalDimmed = styled.div`
  z-index: 9600;
  position: fixed;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
  background: rgba(0, 0, 0, 0.6);
`

export const ConfirmModalPopup = styled.div`
  min-width: 300px;
  min-height: 200px;
  position: fixed;
  top: ${props => (props.pointY ? props.pointY + 'px' : '50%')};
  left: ${props => (props.pointX ? props.pointX + 'px' : '50%')};
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);

  background-color: #fff;
  border-radius: 10px;
  border: 1px solid var(--ecogreen-01);
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: space-around;
  padding: 30px 50px;
`

export const ModalMessage = styled.div`
  width: 100%;
  text-align: center;
  white-space: pre-wrap;
  line-height: 2rem;
`

export const ModalBtnWrap = styled.div`
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
`

export const ModalButton = styled.button`
  color: ${props => (props.cancel ? '#666' : '#fff')};
  background-color: ${props => (props.cancel ? 'none' : 'var(--ecogreen-01)')};
  border: ${props => (props.cancel ? '1px solid #666' : 'none')};
  padding: 12px;
  border-radius: 10px;

  &:hover {
    filter: brightness(0.85);
  }
`

import { ConfirmModalDimmed, ConfirmModalPopup, ModalMessage, ModalBtnWrap, ModalButton } from './ConfirmModalStyle'
import ModalPortal from './ModalPortal'

export const ConfirmModal = props => {
  const { msg, fnCancel, fnConfirm, position = { x: false, y: false } } = props
  if (!msg) return null

  return (
    <ModalPortal>
      <ConfirmModalDimmed onClick={() => fnCancel?.()}>
        <ConfirmModalPopup pointX={position.x} pointY={position.y}>
          <ModalMessage>{msg}</ModalMessage>
          <ModalBtnWrap>
            <ModalButton type="button" cancel={true} onClick={() => fnCancel?.()}>
              Cancel
            </ModalButton>
            <ModalButton type="button" cancel={false} onClick={() => fnConfirm?.()}>
              Confirm
            </ModalButton>
          </ModalBtnWrap>
        </ConfirmModalPopup>
      </ConfirmModalDimmed>
    </ModalPortal>
  )
}

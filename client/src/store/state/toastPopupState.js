import { atom } from 'recoil'

// type : success(성공), error(에러), warning(경고), info(정보), null(기본 팝업)
const toastPopupState = atom({
  key: 'toastPopupState',
  default: { type: undefined, msg: undefined, duration: 1000 },
})

export default toastPopupState

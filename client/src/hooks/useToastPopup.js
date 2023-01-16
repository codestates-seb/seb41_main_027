import { useEffect } from 'react'
import { toast } from 'react-toastify'
import { useRecoilValue } from 'recoil'
import toastPopupState from '../store/state/toastPopupState'

const useToastPopup = () => {
  const toastPopup = useRecoilValue(toastPopupState)

  useEffect(() => {
    const { type, msg, duration = 1000 } = toastPopup
    if (!msg) return

    switch (type) {
      case 'success':
        toast.success(msg, { autoClose: duration })
        break
      case 'error':
        toast.error(msg, { autoClose: duration })
        break
      case 'warning':
        toast.warning(msg, { autoClose: duration })
        break
      case 'info':
        toast.info(msg, { autoClose: duration })
        break
      default:
        toast(msg, { autoClose: duration })
        break
    }
  }, [toastPopup])
}

export default useToastPopup

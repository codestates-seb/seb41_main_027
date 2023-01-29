import { toast } from 'react-toastify'

import { customAxios } from '../utils/customAxios'
import { API_MAIL_ENDPOINT } from '../utils/const'

// mail axios CRUD

// report mail send
export const sendReportMail = async (body, callback) => {
  try {
    const result = await customAxios.post(API_MAIL_ENDPOINT, body)
    toast.success('오류제보를 완료하였습니다.')
    if (callback) callback()
    return result.data
  } catch (error) {
    console.error(error)
    toast.error('데이터 처리에 실패했습니다.')
  }
}

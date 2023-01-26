import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_MEMBER_ENDPOINT } from '../utils/const'

// member axios CRUD

// get member info
export const getMemberInfo = async () => {
  const result = await customAxios.get(API_MEMBER_ENDPOINT)
  return result.data
}

// update
export const updateMemberInfo = async body => {
  try {
    const resultMsg = body.nickName ? '닉네임이' : '비밀번호가'
    const result = await customAxios.patch(API_MEMBER_ENDPOINT, body)
    toast.success(resultMsg + ' 수정되었습니다.')
    return result.data
  } catch (error) {
    console.error(error)
    return toast.error('데이터 처리에 실패했습니다.')
  }
}

// delete
export const deleteMember = async () => {
  try {
    const result = await customAxios.delete(API_MEMBER_ENDPOINT)
    toast.success('탈퇴 처리되었습니다.')
    return result.data
  } catch (error) {
    console.error(error)
    toast.error('데이터 처리에 실패했습니다.')
  }
}

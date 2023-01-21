import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_REVIEW_ENDPOINT } from '../utils/const'

// 리뷰 리스트 조회
export const getReviewList = async (pId, page) => {
  const result = await customAxios.get(`${API_REVIEW_ENDPOINT}/${pId}?page=${page}`)
  return result.data
}

// 리뷰 등록
export const createReviewInfo = async body => {
  try {
    const result = await customAxios.post(API_REVIEW_ENDPOINT, body)
    toast.success('정상적으로 등록되었습니다')
    return result.data
  } catch (error) {
    console.error(error)
    toast.error(error.message)
  }
}

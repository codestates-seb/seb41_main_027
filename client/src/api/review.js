import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_REVIEW_ENDPOINT } from '../utils/const'

// review axios CRUD

// get review list
export const getReviewList = async (pId, page, size) => {
  const result = await customAxios.get(`${API_REVIEW_ENDPOINT}/${pId}?page=${page}&size=${size}`)
  return result.data
}

// create
export const createReviewInfo = async body => {
  try {
    const result = await customAxios.post(API_REVIEW_ENDPOINT, body)
    toast.success('리뷰가 등록되었습니다')
    return result.data
  } catch (error) {
    console.error(error)
    toast.error('데이터 처리에 실패했습니다.')
  }
}

// delete
export const deleteReviewInfo = async (placeId, reviewId) => {
  try {
    const result = await customAxios.delete(`${API_REVIEW_ENDPOINT}/${placeId}/${reviewId}`)
    toast.success('리뷰가 삭제되었습니다.')
    return result.data
  } catch (error) {
    console.error(error)
    toast.error('데이터 처리에 실패했습니다.')
  }
}

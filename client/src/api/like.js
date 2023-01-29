import { toast } from 'react-toastify'

import { customAxios } from '../utils/customAxios'
import { API_LIKE_ENDPOINT } from '../utils/const'

// like axios CRUD

// get like list
export const getLikeList = async (page, size) => {
  const result = await customAxios.get(`${API_LIKE_ENDPOINT}?page=${page}&size=${size}`)
  return result.data
}

// update
export const updateLike = async (pId, isLiked) => {
  try {
    const resultMsg = isLiked ? '추천 되었습니다.' : '추천이 취소되었습니다.'
    const result = await customAxios.post(`${API_LIKE_ENDPOINT}/${pId}`)
    toast.success(resultMsg)
    return result.data
  } catch (error) {
    console.error(error)
    toast.error('데이터 처리에 실패했습니다.')
  }
}

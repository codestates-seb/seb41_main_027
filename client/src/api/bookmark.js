import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_BOOKMARK_ENDPOINT } from '../utils/const'

// bookmark axios CRUD

// get bookmark list
export const getBookmarkList = async (page, size) => {
  const result = await customAxios.get(`${API_BOOKMARK_ENDPOINT}?page=${page}&size=${size}`)
  return result.data
}

// update
export const updateBookmark = async (pId, isBookmark) => {
  try {
    const resultMsg = isBookmark ? '북마크에 등록되었습니다.' : '북마크가 삭제되었습니다.'
    const result = await customAxios.post(`${API_BOOKMARK_ENDPOINT}/${pId}`)
    toast.success(resultMsg)
    return result.data
  } catch (error) {
    console.error(error)
    toast.error('데이터 처리에 실패했습니다.')
  }
}

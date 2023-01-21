import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_PLACE_ENDPOINT } from '../utils/const'

// ----- 장소 관련 axios CURD 정의 -----

// 장소 상세보기 단건 조회
export const getPlaceInfo = async pId => {
  const result = await customAxios.get(`${API_PLACE_ENDPOINT}/${pId}`)
  return result.data
}

// 장소 설명 수정
export const updatePlaceDescription = async (pId, body) => {
  try {
    const result = await customAxios.patch(`${API_PLACE_ENDPOINT}/${pId}`, body)
    toast.success('정상적으로 수정되었습니다')
    return result.data
  } catch (error) {
    console.error(error)
    toast.error(error.message)
  }
}

// 장소 추천 정보 수정
export const updatePlaceLikes = body => {
  customAxios.patch(API_PLACE_ENDPOINT, body)
}

import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_PLACE_ENDPOINT } from '../utils/const'

// place axios CRUD

// get place info
export const getPlaceInfo = async pId => {
  const result = await customAxios.get(`${API_PLACE_ENDPOINT}/${pId}`)
  return result.data
}

// update place description
export const updatePlaceDescription = async (pId, body) => {
  try {
    const result = await customAxios.patch(`${API_PLACE_ENDPOINT}/${pId}`, body)
    toast.success('장소 상세정보가 수정되었습니다')
    return result.data
  } catch (error) {
    console.error(error)
    toast.error(error.message)
  }
}

// 장소 list 가져오기 이상없음..
export const getPlace = async sort => {
  const result = await customAxios.get(`${API_PLACE_ENDPOINT}?sortby=${sort}`)
  console.log('axios :', `${API_PLACE_ENDPOINT}?sortby=${sort}`)
  return result.data
}

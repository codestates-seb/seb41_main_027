import { customAxios } from '../utils/customAxios'
import { toast } from 'react-toastify'
import { API_PLACE_ENDPOINT, API_SEARCH_ENDPOINT } from '../utils/const'

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
    return toast.error('데이터 처리에 실패했습니다.')
  }
}

// 장소 list 가져오기 이상없음..
export const getPlace = async (sort, categoryId, page) => {
  let params = `?sortby=${sort}&id=${categoryId}`
  if (page) params += `&page=${page}`

  const result = await customAxios.get(API_PLACE_ENDPOINT + params)

  // console.log(result)
  return result.data
}

// create
export const createPlace = async body => {
  try {
    const result = await customAxios.post(API_PLACE_ENDPOINT, body)
    toast.success('장소가 등록되었습니다. ')
    return result.data
  } catch (error) {
    console.log(error)
    if (error.response.data.status && error.response.data.status === 409) {
      toast.error(' 이미 등록된 곳입니다. ')
    } else toast.error(error.message)
  }
}

// keyword search
export const keywordSearch = async keyword => {
  try {
    const result = await customAxios.get(API_SEARCH_ENDPOINT + `?keyword=${keyword}`)
    // console.log('result.data', result.data)
    if (result.data.placeList.length === 0) {
      toast.success('정보가 없습니다.')
    }
    return result.data
  } catch (error) {
    console.log(error)
    toast.error('2글자 이상 검색하세요!')
  }
}

// place delete(only admin member)
export const deletePlace = async placeId => {
  try {
    const result = await customAxios.delete(`${API_PLACE_ENDPOINT}/${placeId}`)
    toast.success('장소가 삭제되었습니다.')
    return result.data
  } catch (error) {
    console.error(error)
    return toast.error('데이터 처리에 실패했습니다.')
  }
}

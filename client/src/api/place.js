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
export const getPlace = async (sort, id) => {
  const sortAndId = `?sortby=${sort}&id=${id}`
  const result = await customAxios.get(`${API_PLACE_ENDPOINT}` + sortAndId)
  function removeEmptyParams(query) {
    return query.replace(/[^=&]+=(?:&|$)/g, ' ')
  }
  // console.log(`${API_PLACE_ENDPOINT}?sortby=${sort}`)
  // console.log('sort : ' + sort)
  // console.log('result.data : ', result.data)
  removeEmptyParams(sortAndId)
  console.log(result)
  return result.data
}

// function removeEmptyParams(query) {
//   return query.replace(/[^=&]+=(?:&|$)/g, ' ')
// }

// const testQuery = 'f=1&search=&state_id=2&foo=&bar=12'
// console.info(removeEmptyParams(testQuery))

import { useQuery } from '@tanstack/react-query'
import { getPlaceInfo } from '../api/place'

// ----- 장소 관련 쿼리 정의(only Get) -----

const { REACT_APP_QUERY_STALETIME, REACT_APP_QUERY_RETRY } = process.env

// 장소 상세보기 단건 조회
export const getPlaceInfoById = pId => {
  const { isLoading, isFetching, isError, data, error } = useQuery(
    ['getPlaceInfoById', pId],
    ({ queryKey }) => getPlaceInfo(queryKey[1]),
    { enabled: !!pId, staleTime: REACT_APP_QUERY_STALETIME, retry: REACT_APP_QUERY_RETRY },
  )

  return { isLoading, isFetching, isError, data, error }
}

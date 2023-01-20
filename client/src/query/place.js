import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as place from '../api/place'

// ----- 장소 관련 쿼리 정의(only Get) -----

// 장소 상세보기 단건 조회
export const useGetPlaceInfoById = pId => {
  return useQuery(['getPlaceInfoById', pId], ({ queryKey }) => place.getPlaceInfo(queryKey[1]), {
    enabled: !!pId,
    staleTime: QUERY_STALETIME,
    retry: QUERY_RETRY,
  })
}

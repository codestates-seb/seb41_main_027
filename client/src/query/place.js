import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as place from '../api/place'
import { useRecoilState, useRecoilValue } from 'recoil'
import { placeSort } from '../recoil/atoms'

// ----- 장소 관련 쿼리 정의(only Get) -----

// 장소 상세보기 단건 조회
export const useGetPlaceInfoById = pId => {
  return useQuery(['getPlaceInfoById', pId], ({ queryKey }) => place.getPlaceInfo(queryKey[1]), {
    enabled: !!pId,
    staleTime: QUERY_STALETIME,
    retry: QUERY_RETRY,
  })
}

// 여기가 잘못된거 같음... 확인바람...
// 장소 list 모두 조회
// export const useGetPlaceList = () => {
//   const { data } = useQuery(['getPlace', page, sort], () => place.getPlaceList(), {
//     staleTime: QUERY_STALETIME,
//     notifyOnChangeProps: 'tracked',
//     retry: false,
//   })
//   return data
// }
export const useGetPlace = sort => {
  return useQuery(['getPlace', sort], ({ queryKey }) => place.getPlace(queryKey[1]), {
    enabled: !!sort,
    staleTime: QUERY_STALETIME,
    retry: false,
  })
}

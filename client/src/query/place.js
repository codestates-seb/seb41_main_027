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

export const useGetPlace = (sort, categoryId) => {
  return useQuery(['getPlace', sort, categoryId], ({ queryKey }) => place.getPlace(queryKey[1], queryKey[2]), {
    enabled: !!sort,
    staleTime: QUERY_STALETIME,
    retry: false,
  })
}

export const useKeywordSearch = keyword => {
  return useQuery(['keywordSearch', keyword], ({ queryKey }) => place.keywordSearch(queryKey[1]), {
    enabled: !!keyword,
    staleTime: QUERY_STALETIME,
    retry: false,
  })
}

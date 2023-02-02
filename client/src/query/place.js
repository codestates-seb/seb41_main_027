import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as place from '../api/place'
import { useRecoilState, useRecoilValue } from 'recoil'
import { placeSort } from '../recoil/atoms'

const baseConfig = { staleTime: QUERY_STALETIME, retry: QUERY_RETRY }

// 장소 상세보기 단건 조회
export const useGetPlaceInfoById = pId => {
  return useQuery(['getPlaceInfoById', pId], ({ queryKey }) => place.getPlaceInfo(queryKey[1]), {
    ...baseConfig,
    enabled: !!pId,
  })
}

export const useGetPlace = (sort, categoryId, page) => {
  return useQuery(
    ['getPlace', sort, categoryId, page],
    ({ queryKey }) => place.getPlace(queryKey[1], queryKey[2], queryKey[3]),
    {
      enabled: !!sort,
      staleTime: QUERY_STALETIME,
      retry: false,
    },
  )
}

export const useKeywordSearch = keyword => {
  return useQuery(['keywordSearch', keyword], ({ queryKey }) => place.keywordSearch(queryKey[1]), {
    enabled: !!keyword,
    staleTime: QUERY_STALETIME,
    retry: false,
  })
}

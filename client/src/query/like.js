import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as likeApi from '../api/like'

// ----- 북마크 관련 쿼리 정의(only Get) -----

// 북마크 리스트 조회
export const useGetLikeList = (page, size) => {
  return useQuery(['getLikeList', page, size], ({ queryKey }) => likeApi.getLikeList(queryKey[1], queryKey[2]), {
    enabled: !!page,
    staleTime: QUERY_STALETIME,
    retry: QUERY_RETRY,
  })
}

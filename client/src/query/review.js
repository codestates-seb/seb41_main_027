import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as review from '../api/review'

// ----- 리뷰 관련 쿼리 정의(only Get) -----

// 리뷰 리스트 조회
export const useGetReviewListById = (pId, page, createdReivewId) => {
  return useQuery(
    ['getReviewListById', pId, page, createdReivewId],
    ({ queryKey }) => review.getReviewList(queryKey[1], queryKey[2]),
    { enabled: !!pId, staleTime: QUERY_STALETIME, retry: QUERY_RETRY },
  )
}

import { useQuery } from '@tanstack/react-query'

import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as review from '../api/review'

const baseConfig = { staleTime: QUERY_STALETIME, retry: QUERY_RETRY }

// 리뷰 리스트 조회
export const useGetReviewListById = (pId, page, size, createdReivewId) => {
  return useQuery(
    ['getReviewListById', pId, page, size, createdReivewId],
    ({ queryKey }) => review.getReviewList(queryKey[1], queryKey[2], queryKey[3]),
    { ...baseConfig, enabled: !!pId },
  )
}

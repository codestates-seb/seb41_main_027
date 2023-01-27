import { useEffect } from 'react'
import { useQuery } from '@tanstack/react-query'
import { useSetRecoilState } from 'recoil'

import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as review from '../api/review'
import { reviewTotalCntByPlaceId } from '../recoil/reviewState'

// ----- 리뷰 관련 쿼리 정의(only Get) -----

// 리뷰 리스트 조회
export const useGetReviewListById = (pId, page, size, createdReivewId) => {
  const { isLoading, isFetching, isError, error, data } = useQuery(
    ['getReviewListById', pId, page, size, createdReivewId],
    ({ queryKey }) => review.getReviewList(queryKey[1], queryKey[2], queryKey[3]),
    { enabled: !!pId, staleTime: QUERY_STALETIME, retry: QUERY_RETRY },
  )

  // total review cnt state save
  const setTotalReviewCntByPlaceId = useSetRecoilState(reviewTotalCntByPlaceId)
  useEffect(() => {
    if (data) setTotalReviewCntByPlaceId(data.totalElements)
  }, [data, setTotalReviewCntByPlaceId])

  return { isLoading, isFetching, isError, error, data }
}

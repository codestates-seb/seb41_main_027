import { useQuery } from '@tanstack/react-query'
import { getReviewList } from '../api/review'

// ----- 리뷰 관련 쿼리 정의(only Get) -----

const { REACT_APP_QUERY_STALETIME, REACT_APP_QUERY_RETRY } = process.env

// 리뷰 리스트 조회
export const getReviewListById = (pId, page, addReviewId) => {
  const { isLoading, isFetching, isError, data, error } = useQuery(
    ['getReviewListById', pId, page, addReviewId],
    ({ queryKey }) => getReviewList(queryKey[1], queryKey[2]),
    { enabled: !!pId, staleTime: REACT_APP_QUERY_STALETIME, retry: REACT_APP_QUERY_RETRY },
  )

  return { isLoading, isFetching, isError, data, error }
}

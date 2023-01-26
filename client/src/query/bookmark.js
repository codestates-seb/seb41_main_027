import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as bookmark from '../api/bookmark'

// ----- 북마크 관련 쿼리 정의(only Get) -----

// 북마크 리스트 조회
export const useGetBookmarkList = (page, size) => {
  return useQuery(
    ['getBookmarkList', page, size],
    ({ queryKey }) => bookmark.getBookmarkList(queryKey[1], queryKey[2]),
    { enabled: !!page, staleTime: QUERY_STALETIME, retry: QUERY_RETRY },
  )
}

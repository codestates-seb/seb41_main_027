import { useQuery } from '@tanstack/react-query'
import { QUERY_STALETIME, QUERY_RETRY } from '../utils/const'
import * as member from '../api/member'

// ----- 회원 관련 쿼리 정의(only Get) -----

// get member info
export const useGetMemberInfoById = memberId => {
  return useQuery(['getMemberInfoById', memberId], ({ queryKey }) => member.getMemberInfo(), {
    enabled: !!memberId,
    staleTime: QUERY_STALETIME,
    retry: QUERY_RETRY,
  })
}

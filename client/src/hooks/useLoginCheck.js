import { useEffect } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'

import { getLoginInfo } from '../api/login'

const useLoginCheck = type => {
  const { pathname } = useLocation()
  const navigate = useNavigate()
  const loginMemberId = getLoginInfo().id

  useEffect(() => {
    if (!loginMemberId) {
      switch (type) {
        case 'redirect':
          navigate('/signin', { state: { callbackUrl: pathname } })
          break

        default:
          navigate('/signin')
      }
    }
  }, [])

  if (!loginMemberId) return null
}

export default useLoginCheck

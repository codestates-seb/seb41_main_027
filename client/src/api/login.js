import { customAxios } from '../utils/customAxios'
import { API_REISSUE_ENDPOINT } from '../utils/const'

export const getLoginInfo = () => {
  const id = localStorage.getItem('id') ? Number(localStorage.getItem('id')) : undefined
  const aToken = localStorage.getItem('aToken')
  const rToken = localStorage.getItem('rToken')

  return { id, aToken, rToken }
}

export const setLoginInfo = (memberId, accessToken, refreshToken) => {
  localStorage.clear()
  localStorage.setItem('id', memberId)
  localStorage.setItem('aToken', accessToken)
  localStorage.setItem('rToken', refreshToken)
}

export const resetAccessToken = accessToken => {
  localStorage.removeItem('aToken')
  localStorage.setItem('aToken', accessToken)
}

// access token invalid > refresh token > reissue access token
export const reissueAccessToken = async () => {
  try {
    const result = await customAxios.post(`${API_REISSUE_ENDPOINT}`)
    return result.headers.authorization
  } catch (error) {
    console.error('reissue access token error', error)
    // refresh token reissue fail > login page
    window.location.href = '/signin'
  }
}

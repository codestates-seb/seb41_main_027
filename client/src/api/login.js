export const getLoginInfo = () => {
  const id = localStorage.getItem('id') ? Number(localStorage.getItem('id')) : undefined
  const nickName = localStorage.getItem('nickName')
  const aToken = localStorage.getItem('aToken')
  const rToken = localStorage.getItem('rToken')

  return { id, nickName, aToken, rToken }
}

export const setLoginInfo = (memberId, nickName, accessToken, refreshToken) => {
  localStorage.clear()
  localStorage.setItem('id', memberId)
  localStorage.setItem('nickName', nickName)
  localStorage.setItem('aToken', accessToken)
  localStorage.setItem('rToken', refreshToken)
}

export const removeLoginInfo = () => {
  localStorage.clear()
}

export const resetAccessToken = accessToken => {
  localStorage.removeItem('aToken')
  localStorage.setItem('aToken', accessToken)
}

export const resetNickName = nickName => {
  localStorage.removeItem('nickName')
  localStorage.setItem('nickName', nickName)
}

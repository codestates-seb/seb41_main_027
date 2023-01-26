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

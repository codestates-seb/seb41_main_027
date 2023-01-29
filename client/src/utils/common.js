// return promise
export const clipboardCopy = copyData => {
  return navigator.clipboard.writeText(copyData)
}

// 천 단위 콤마 구분(10000 => 10,000)
export const amountDisplay = number => {
  return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
}

// 현재 시간 기준 날짜 변환(1 min ago, 2 mins ago, 1 sec ago)
export const dateConvert = createAt => {
  if (!createAt) return

  const cDate = new Date(createAt)
  // const cDate = utcDate.getTime() - utcDate.getTimezoneOffset() * 60 * 1000

  const milliSeconds = new Date() - cDate

  const seconds = milliSeconds / 1000
  if (seconds < 60) return `${Math.floor(seconds)}초 전`

  const minutes = seconds / 60
  if (minutes < 60) return `${Math.floor(minutes)}분 전`

  const hours = minutes / 60
  if (hours < 60) return `${Math.floor(hours)}시간 전`

  const days = hours / 24
  if (days < 7) return `${Math.floor(days)}일 전`

  const weeks = days / 7
  if (weeks < 5) return `${Math.floor(weeks)}주 전`

  const lastDayOfMonth = new Date(cDate.getFullYear(), cDate.getMonth() + 1, 0).getDate()
  const months = days / lastDayOfMonth
  if (months < 12) return `${Math.floor(months)}달 전`

  const years = days / 365
  return `${Math.floor(years)}년 전`
}

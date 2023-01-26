// api
export const API_PLACE_ENDPOINT = '/places'
export const API_BOOKMARK_ENDPOINT = '/bookmarks'
export const API_MEMBER_ENDPOINT = '/members'
export const API_REVIEW_ENDPOINT = '/reviews'
export const API_LIKE_ENDPOINT = '/likes'
export const API_LOGIN_ENDPOINT = '/auth/login'
export const API_LOGOUT_ENDPOINT = '/auth/logout'
export const API_REISSUE_ENDPOINT = '/reissue'
export const API_CONNECT_TIMEOUT = 2000
export const QUERY_RETRY = 1
export const QUERY_STALETIME = 20000

// regular expression
export const MEMBER_NICKNAME_REGEX = /^[0-9a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ]{2,12}$/
export const MEMBER_PWD_REGEX = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}/

// emoji
export const EMOJI_LIST = [
  { id: 1, emoji: '😀' },
  { id: 2, emoji: '🤔' },
  { id: 3, emoji: '🙂' },
  { id: 4, emoji: '😍' },
  { id: 5, emoji: '🤥' },
  { id: 6, emoji: '🤩' },
  { id: 7, emoji: '🥹' },
  { id: 8, emoji: '😧' },
  { id: 9, emoji: '😂' },
  { id: 10, emoji: '😆' },
]

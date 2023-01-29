// api
export const API_PLACE_ENDPOINT = '/places'
export const API_SEARCH_ENDPOINT = '/places/search'
export const API_BOOKMARK_ENDPOINT = '/bookmarks'
export const API_MEMBER_ENDPOINT = '/members'
export const API_REVIEW_ENDPOINT = '/reviews'
export const API_LIKE_ENDPOINT = '/likes'
export const API_LOGIN_ENDPOINT = '/auth/login'
export const API_LOGOUT_ENDPOINT = '/auth/logout'
export const API_REISSUE_ENDPOINT = '/reissue'
export const API_MAIL_ENDPOINT = '/mail'
export const API_CONNECT_TIMEOUT = 5000
export const QUERY_RETRY = 1
export const QUERY_STALETIME = 20000

// regular expression
export const MEMBER_NICKNAME_REGEX = /^[0-9a-zA-Zㄱ-힣]{2,12}$/
export const MEMBER_PWD_REGEX = /^[#?!@$%^&*a-zA-Z0-9]{8,16}$/ ///^[#?!@$%^&*a-zA-Z0-9]{8,16}$/

// emoji
import {
  emojiIcon1,
  emojiIcon2,
  emojiIcon3,
  emojiIcon4,
  emojiIcon5,
  emojiIcon6,
  emojiIcon7,
  emojiIcon8,
  emojiIcon9,
  emojiIcon10,
} from '../assets/emoji'
export const EMOJI_LIST = [
  { id: 1, icon: emojiIcon1, alt: 'emoji happy' },
  { id: 2, icon: emojiIcon2, alt: 'emoji concern' },
  { id: 3, icon: emojiIcon3, alt: 'emoji smile' },
  { id: 4, icon: emojiIcon4, alt: 'emoji crazy love' },
  { id: 5, icon: emojiIcon5, alt: 'emoji unhappy' },
  { id: 6, icon: emojiIcon6, alt: 'emoji satisfaction' },
  { id: 7, icon: emojiIcon7, alt: 'emoji pleading' },
  { id: 8, icon: emojiIcon8, alt: 'emoji anguished' },
  { id: 9, icon: emojiIcon9, alt: 'emoji relieved laughter' },
  { id: 10, icon: emojiIcon10, alt: 'emoji persevere' },
]

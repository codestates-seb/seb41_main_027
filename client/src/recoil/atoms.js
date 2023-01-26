import { atom } from 'recoil'

export const listClick = atom({
  key: 'listClick',
  default: {
    lat: 33.452613,
    lng: 126.570888,
  },
})

export const searchValue = atom({
  key: 'searchValue',
  default: '',
})

export const placeSort = atom({
  key: 'placeSort',
  default: 'default',
})

export const placePage = atom({
  key: 'placePage',
  default: 1,
})

export const placesAll = atom({
  key: 'placesAll',
  default: '',
})

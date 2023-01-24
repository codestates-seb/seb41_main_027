import { atom } from 'recoil'

export const listClick = atom({
  key: 'listClick',
  default: {
    lat: 37.553505782513895,
    lng: 126.97077939714491,
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

export const addPlaceInfo = atom({
  key: 'addPlaceInfo',
  default: [],
})

export const categoryValue = atom({
  key: 'categoryValue',
  default: '',
})

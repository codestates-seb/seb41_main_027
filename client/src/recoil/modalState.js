import { atom } from 'recoil'

export const placeInfoModalOpen = atom({
  key: 'placeInfoModalOpen',
  default: false,
})

export const reportModalOpen = atom({
  key: 'reportModalOpen',
  default: false,
})

export const reportModalSubject = atom({
  key: 'reportModalSubject',
  default: null,
})

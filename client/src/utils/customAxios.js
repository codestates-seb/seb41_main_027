import axios from 'axios'
import { API_CONNECT_TIMEOUT } from './const'
import { getLoginInfo } from '../api/login'

const { REACT_APP_API_URL } = process.env
const { aToken, rToken } = getLoginInfo()

export const customAxios = axios.create({
  baseURL: REACT_APP_API_URL,
  headers: {
    'Content-Type': 'application/json',
    Authorization: aToken,
    Refresh: rToken,
  },
  timeout: API_CONNECT_TIMEOUT,
  withCredentials: true,
})

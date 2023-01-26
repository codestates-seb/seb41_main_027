import axios from 'axios'
import { API_CONNECT_TIMEOUT } from './const'
import { getLoginInfo } from '../api/login'

const { REACT_APP_API_URL } = process.env

export const customAxios = axios.create({
  baseURL: REACT_APP_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: API_CONNECT_TIMEOUT,
  withCredentials: true,
})

customAxios.interceptors.request.use(
  function (config) {
    const { aToken, rToken } = getLoginInfo()
    if (aToken) config.headers.Authorization = aToken
    if (rToken) config.headers.Refresh = rToken

    return config
  },
  function (error) {
    if (error.status === 401) {
      const { rToken } = getLoginInfo()
      //reissue 로직 추가
      if (rToken) console.log('refresh 로직 추가')
    }
    // return Promise.reject(error)
  },
)

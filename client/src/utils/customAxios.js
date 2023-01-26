import axios from 'axios'

import { API_CONNECT_TIMEOUT } from './const'
import * as loginApi from '../api/login'

const { REACT_APP_API_URL } = process.env

export const customAxios = axios.create({
  baseURL: REACT_APP_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: API_CONNECT_TIMEOUT,
  withCredentials: true,
})

customAxios.interceptors.request.use(function (config) {
  const { aToken, rToken } = loginApi.getLoginInfo()

  // header token setting
  if (aToken) config.headers.Authorization = aToken
  if (rToken) config.headers.Refresh = rToken

  return config
})

customAxios.interceptors.response.use(
  function (response) {
    return response
  },
  function (error) {
    const errorData = error.response.data

    // Access Token reissue
    if (errorData && errorData.status === 401 && errorData.message === 'Invalid Access Token') {
      loginApi
        .reissueAccessToken()
        .then(data => {
          // Access Token update
          loginApi.resetAccessToken(data)
        })
        .catch(() => {})

      return Promise.reject(error)
      //return new Promise(() => {})
    }

    return Promise.reject(error)
  },
)

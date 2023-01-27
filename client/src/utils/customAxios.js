import axios from 'axios'

import { API_CONNECT_TIMEOUT, API_REISSUE_ENDPOINT } from './const'
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
  async function (error) {
    const { status, message } = error.response.data
    // Access Token reissue
    if (status && status === 401) {
      try {
        const originalRequest = error.config
        const result = await customAxios.post(API_REISSUE_ENDPOINT)

        const newAccessToken = result.headers.authorization
        if (newAccessToken) {
          // new Access Token save
          loginApi.resetAccessToken(newAccessToken)

          // request header setting
          originalRequest.headers.Authorization = newAccessToken
          return await customAxios.request(originalRequest)
        }
      } catch (error) {
        // reissue fail
        loginApi.removeLoginInfo()
        console.log(error)
        window.location.href = '/signin'
      }

      return Promise.reject(error)
    }

    return Promise.reject(error)
  },
)

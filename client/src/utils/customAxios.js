import axios from 'axios'

const { REACT_APP_API, REACT_APP_API_CONNECT_TIMEOUT } = process.env

// token add header : Authorization: token
export const customAxios = axios.create({
  baseURL: REACT_APP_API,
  headers: { 'Content-Type': 'application/json' },
  timeout: REACT_APP_API_CONNECT_TIMEOUT,
  withCredentials: true,
})

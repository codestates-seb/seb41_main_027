import axios from 'axios'
import { API_CONNECT_TIMEOUT } from './const'

const { REACT_APP_API_URL } = process.env

// token add header : Authorization: token
export const customAxios = axios.create({
  baseURL: REACT_APP_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: API_CONNECT_TIMEOUT,
  withCredentials: true,
})

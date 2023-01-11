import axios from 'axios'

const API_BOOKMARK_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_BOOKMARK_ENDPOINT
const API_CONNECT_TIMEOUT = 2000

// create
export const bookmarkCreate = body => {
  axios.post(API_BOOKMARK_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const bookmarkUpdate = body => {
  axios.patch(API_BOOKMARK_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const bookmarkDelete = () => {
  axios.delete(API_BOOKMARK_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

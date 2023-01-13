import axios from 'axios'

const API_BOOKMARK_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_BOOKMARK_ENDPOINT
const API_CONNECT_TIMEOUT = process.env.REACT_APP_API_CONNECT_TIMEOUT

// create
export const createBookmark = body => {
  axios.post(API_BOOKMARK_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const updateBookmark = body => {
  axios.patch(API_BOOKMARK_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const deleteBookmark = () => {
  axios.delete(API_BOOKMARK_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

import axios from 'axios'

const API_REVIEW_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_REVIEW_ENDPOINT
const API_CONNECT_TIMEOUT = 2000

// create
export const reviewCreate = body => {
  axios.post(API_REVIEW_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const reviewUpdate = body => {
  axios.patch(API_REVIEW_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const reviewDelete = () => {
  axios.delete(API_REVIEW_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

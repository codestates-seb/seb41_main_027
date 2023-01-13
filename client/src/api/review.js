import axios from 'axios'

const API_REVIEW_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_REVIEW_ENDPOINT
const API_CONNECT_TIMEOUT = process.env.REACT_APP_API_CONNECT_TIMEOUT

// create create
export const createReview = body => {
  axios.post(API_REVIEW_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const updateReview = body => {
  axios.patch(API_REVIEW_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const deleteReview = () => {
  axios.delete(API_REVIEW_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

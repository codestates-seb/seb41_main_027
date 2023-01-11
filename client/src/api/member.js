import axios from 'axios'

const API_MEMBER_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_MEMBER_ENDPOINT
const API_CONNECT_TIMEOUT = 2000

// create
export const memberCreate = body => {
  axios.post(API_MEMBER_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const memberUpdate = body => {
  axios.patch(API_MEMBER_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const memberDelete = () => {
  axios.delete(API_MEMBER_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

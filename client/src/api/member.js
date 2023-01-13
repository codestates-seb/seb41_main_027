import axios from 'axios'

export const API_MEMBER_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_MEMBER_ENDPOINT
const API_CONNECT_TIMEOUT = process.env.REACT_APP_API_CONNECT_TIMEOUT

// create create
export const createMember = body => {
  axios.post(API_MEMBER_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const updateMember = body => {
  axios.patch(API_MEMBER_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const deleteMember = () => {
  axios.delete(API_MEMBER_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

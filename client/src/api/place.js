import axios from 'axios'

const API_PLACE_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_PLACE_ENDPOINT
const API_CONNECT_TIMEOUT = 2000

// create
export const placeCreate = body => {
  axios.post(API_PLACE_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const placeUpdate = body => {
  axios.patch(API_PLACE_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const placeDelete = () => {
  axios.delete(API_PLACE_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

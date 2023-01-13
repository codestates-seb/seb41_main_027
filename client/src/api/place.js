import axios from 'axios'

const API_PLACE_ENDPOINT = process.env.REACT_APP_API + process.env.REACT_APP_API_PLACE_ENDPOINT
const API_CONNECT_TIMEOUT = process.env.REACT_APP_API_CONNECT_TIMEOUT

// create create
export const createPlace = body => {
  axios.post(API_PLACE_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// update
export const updatePlace = body => {
  axios.patch(API_PLACE_ENDPOINT, body, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

// delete
export const deletePlace = () => {
  axios.delete(API_PLACE_ENDPOINT, {
    timeout: API_CONNECT_TIMEOUT,
  })
}

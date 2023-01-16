import { useState, useEffect } from 'react'
import axios from 'axios'

const API_CONNECT_TIMEOUT = process.env.REACT_APP_API_CONNECT_TIMEOUT

const useFetch = (url, params, reload) => {
  const [data, setData] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState(null)

  const getFetch = async (url, params, reload) => {
    console.log('-- useFetch --')
    console.log('params', params)

    try {
      let config = { timeout: API_CONNECT_TIMEOUT }
      if (params) config.params = params

      const result = await axios.get(url, config)
      console.log('result', result)
      setIsLoading(false)
      setData(result.data)
    } catch (err) {
      console.error(err)
      setIsLoading(false)
      setError(err.message)
    }
  }

  useEffect(() => {
    getFetch(url, params)
  }, [url, params, reload])

  return [data, isLoading, error]
}

export default useFetch

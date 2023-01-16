import { useEffect } from 'react'
import { useLocation } from 'react-router-dom'

// 페이지 변경 시 스크롤을 맨 위로 올려준다.
const ScrollToTop = () => {
  const { pathname } = useLocation()

  useEffect(() => {
    if (window) window.scrollTo(0, 0)
  }, [pathname])
}

export default ScrollToTop

import React, { useEffect } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faComment } from '@fortawesome/free-solid-svg-icons'

const KakaoShareBtn = props => {
  const { title, description, likeCnt, reviewCnt, linkUrl } = props

  useEffect(() => {
    const script = document.createElement('script')
    script.src = 'https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js'
    script.integrity = 'sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx'
    script.crossOrigin = 'anonymous'
    script.async = true
    document.head.appendChild(script)

    script.onerror = () => {
      console.log('Error occurred while loading kakao share script')
    }

    return () => document.head.removeChild(script)
  }, [])

  const sendKakaoShare = () => {
    if (!window.Kakao) return
    const kakao = window.Kakao
    if (!kakao.isInitialized()) kakao.init(process.env.REACT_APP_KAKAO_API_KEY)

    kakao.Share.sendDefault({
      objectType: 'feed',
      content: {
        title: title,
        description: description,
        imageUrl: `${window.location.origin}/thumbnail.png`,
        link: {
          mobileWebUrl: linkUrl,
          webUrl: linkUrl,
        },
      },
      social: {
        likeCount: likeCnt,
        commentCount: reviewCnt,
        sharedCount: 0,
      },
      buttons: [
        {
          title: '자세히 보기',
          link: {
            mobileWebUrl: linkUrl,
            webUrl: linkUrl,
          },
        },
      ],
    })
  }

  return (
    <button className="kakao-share-btn" aria-label="카카오톡 공유하기 버튼" onClick={sendKakaoShare}>
      <FontAwesomeIcon icon={faComment} />
    </button>
  )
}

export default KakaoShareBtn

import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faShareNodes, faBookmark as bookmarkOn } from '@fortawesome/free-solid-svg-icons'
import { faBookmark as bookmarkOff } from '@fortawesome/free-regular-svg-icons'
import { ClipBoardCopy } from '../../utils/common'
import { toast } from 'react-toastify'
import KakaoShareBtn from '../Button/KakaoShareBtn'

const ShareBookmark = props => {
  // console.log('-- (7)ShareBookmark Render --')

  const { kakaoId, name, description, placeLikeCount, placeReviewCount, isBookMarked } = props.item

  // state, hook
  const [isBookmarkState, setIsBookmarkState] = useState(isBookMarked)

  // handle
  const handleClickCopy = () => {
    ClipBoardCopy(window.location.href).then(() => toast.info('URL이 복사되었습니다.'))
  }

  // kakaotalk share link info
  const kakaoShareInfo = {
    title: name,
    description: description,
    likeCnt: placeLikeCount,
    reviewCnt: placeReviewCount,
    linkUrl: window.location.href,
  }

  // react query
  const queryBookmarkUpdate = () => {
    setIsBookmarkState(!isBookmarkState)
  }

  const handleClickKakaoMapMove = () => {
    window.open(`https://map.kakao.com/?itemId=${kakaoId}`, '_blank')
  }

  return (
    <div className="bottom-wrapper">
      <div className="bottom-share">
        <button className="btn-icon" onClick={handleClickCopy}>
          <FontAwesomeIcon icon={faShareNodes} />
        </button>
        {KakaoShareBtn(kakaoShareInfo)}
      </div>
      <div className="bottom-detail-link">
        <button onClick={handleClickKakaoMapMove}>카카오 맵으로 자세히 보기</button>
      </div>
      <div className="bottom-bookmark">
        <button className="btn-icon bookmark" onClick={queryBookmarkUpdate}>
          {isBookmarkState ? <FontAwesomeIcon icon={bookmarkOn} /> : <FontAwesomeIcon icon={bookmarkOff} />}
        </button>
      </div>
    </div>
  )
}

export const MemoShareBookmark = React.memo(ShareBookmark)

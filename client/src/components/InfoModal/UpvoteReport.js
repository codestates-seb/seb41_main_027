import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFlag, faHeart as likeOn } from '@fortawesome/free-solid-svg-icons'
import { faHeart as likeOff } from '@fortawesome/free-regular-svg-icons'

const UpvoteReport = props => {
  console.log('-- (2)UpvoteReport Render --')

  const { placeLikeCount: likeCnt, isLiked } = props.item

  const [likeState, setLikeInfo] = useState({
    isLike: isLiked,
    likeCnt: likeCnt,
  })

  // 장소 추천 등록/취소
  const editLikes = () => {
    // db 정보 받아오기
    const likeCnt = !likeState.isChecked ? likeState.likeCnt + 1 : likeState.likeCnt - 1
    setLikeInfo({ isChecked: !likeState.isChecked, likeCnt: likeCnt })
  }

  return (
    <div className="head-like-report">
      <div className="head-like">
        <button onClick={editLikes}>
          {likeState.isChecked ? <FontAwesomeIcon icon={likeOn} /> : <FontAwesomeIcon icon={likeOff} />}
        </button>
        <span>{likeState.likeCnt}</span>
      </div>
      <div className="head-report">
        <FontAwesomeIcon icon={faFlag} />
        <button onClick={() => window.open('')}>장소 오류 제보하기</button>
      </div>
    </div>
  )
}

export const MemoUpvoteReport = React.memo(UpvoteReport)

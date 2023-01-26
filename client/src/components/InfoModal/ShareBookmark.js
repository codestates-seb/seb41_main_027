import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faShareNodes, faBookmark as bookmarkOn } from '@fortawesome/free-solid-svg-icons'
import { faBookmark as bookmarkOff } from '@fortawesome/free-regular-svg-icons'
import { useRecoilValue } from 'recoil'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

import { InfoShareBookmark } from './ShareBookmarkStyle'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'
import { ClipBoardCopy } from '../../utils/common'
import KakaoShareBtn from '../Button/KakaoShareBtn'
import { reviewCnt } from '../../recoil/reviewState'
import * as bookmarkApi from '../../api/bookmark'

const ShareBookmark = ({ item, queryRefresh }) => {
  console.log('-- (7)ShareBookmark Render --')
  const loginMemberId = 1

  const { placeId: pId, kakaoId, name, description, likeCount, isBookMarked } = item

  // state, hook
  const navigate = useNavigate()
  const [isBookmarkState, setIsBookmarkState] = useState(isBookMarked)
  const [confirmModal, setConfirmModal] = useState({})

  // kakaotalk share link info
  const kakaoShareInfo = {
    title: name,
    description: description,
    likeCnt: likeCount,
    reviewCnt: useRecoilValue(reviewCnt),
    linkUrl: window.location.href,
  }

  // handle
  const handleClickCopy = () => {
    ClipBoardCopy(window.location.href).then(() => toast.info('URL이 복사되었습니다.'))
  }

  const handleBookmarkEdit = () => {
    if (!loginMemberId) {
      setConfirmModal({
        msg: '로그인이 필요합니다.\n로그인 하시겠습니까?',
        fnConfirm: () => navigate('/signin'),
      })
    } else if (loginMemberId) {
      // db update
      bookmarkApi.updateBookmark(pId, !isBookmarkState).then(data => {
        setIsBookmarkState(data)
        queryRefresh()
      })
    }
  }

  const handleClickKakaoMapMove = () => {
    window.open(`https://map.kakao.com/?itemId=${kakaoId}`, '_blank')
  }

  const confirmModalClose = () => {
    setConfirmModal({})
  }

  return (
    <InfoShareBookmark>
      <ConfirmModal
        msg={confirmModal.msg}
        fnCancel={confirmModalClose}
        fnConfirm={confirmModal.fnConfirm}
        position={confirmModal.position}
      />
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
        <button className="btn-icon bookmark" onClick={handleBookmarkEdit}>
          {isBookmarkState && <FontAwesomeIcon icon={bookmarkOn} />}
          {!isBookmarkState && <FontAwesomeIcon icon={bookmarkOff} />}
        </button>
      </div>
    </InfoShareBookmark>
  )
}

export const MemoShareBookmark = React.memo(ShareBookmark)

import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faShareNodes, faBookmark as bookmarkOn } from '@fortawesome/free-solid-svg-icons'
import { faBookmark as bookmarkOff } from '@fortawesome/free-regular-svg-icons'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'
import { useRecoilValue } from 'recoil'

import { InfoShareBookmark } from './ShareBookmarkStyle'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'
import { clipboardCopy } from '../../utils/common'
import KakaoShareBtn from '../Button/KakaoShareBtn'
import * as bookmarkApi from '../../api/bookmark'
import { getLoginInfo } from '../../api/login'
import { reviewTotalCntByPlaceId } from '../../recoil/reviewState'

const ShareBookmark = ({ item, queryRefresh }) => {
  // console.log('-- (7)ShareBookmark Render --')

  const loginMemberId = getLoginInfo().id
  const { placeId: pId, kakaoId, name, description, likeCount, isBookMarked } = item
  const shareLinkUrl = `${window.location.origin}/placeinfo/${pId}`

  // state, hook
  const navigate = useNavigate()
  const [isBookmarkState, setIsBookmarkState] = useState(isBookMarked)
  const [confirmModal, setConfirmModal] = useState({})

  // kakaotalk share link info
  const kakaoShareInfo = {
    title: name,
    description: description,
    likeCnt: likeCount,
    reviewCnt: useRecoilValue(reviewTotalCntByPlaceId),
    linkUrl: shareLinkUrl,
  }

  // handle
  const handleClickCopy = () => {
    clipboardCopy(shareLinkUrl).then(() => toast.info('URL이 복사되었습니다.'))
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
    window.open(`https://place.map.kakao.com/${kakaoId}`, '_blank')
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

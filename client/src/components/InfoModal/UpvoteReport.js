import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFlag, faHeart as likeOn } from '@fortawesome/free-solid-svg-icons'
import { faHeart as likeOff } from '@fortawesome/free-regular-svg-icons'
import { useNavigate } from 'react-router-dom'

import * as likeAPi from '../../api/like'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'
import { InfoUpvoteReport } from './UpvoteReportStyle'
import { getLoginInfo } from '../../api/login'
import ReportModal from '../../pages/Report/ReportModal'

const UpvoteReport = ({ item, queryRefresh }) => {
  // console.log('-- (2)UpvoteReport Render --')

  const loginMemberId = getLoginInfo().id
  const { placeId: pId, name: pName, likeCount: likeCnt, isLiked: isLiked } = item

  // state, hook
  const navigate = useNavigate()
  const [confirmModal, setConfirmModal] = useState({})
  const [reportModalOpen, setReportModalOpen] = useState(false)
  const [likeState, setLikeState] = useState({
    isLiked: isLiked,
    likeCnt: likeCnt,
  })

  // handle
  const handleLikeEdit = () => {
    if (!loginMemberId) {
      setConfirmModal({
        msg: '로그인이 필요합니다.\n로그인 하시겠습니까?',
        fnConfirm: () => navigate('/signin'),
      })
    } else if (loginMemberId) {
      // db update
      likeAPi.updateLike(pId, !likeState.isLiked).then(data => {
        if (data) setLikeState({ isLiked: data.isLiked, likeCnt: data.likeCount })
        queryRefresh()
      })
    }
  }

  const confirmModalClose = () => {
    setConfirmModal({})
  }

  return (
    <InfoUpvoteReport>
      <ConfirmModal
        msg={confirmModal.msg}
        fnCancel={confirmModalClose}
        fnConfirm={confirmModal.fnConfirm}
        position={confirmModal.position}
      />
      {reportModalOpen && <ReportModal subject={[pId, pName]} modalClose={() => setReportModalOpen(false)} />}
      <div className="head-like">
        <button onClick={handleLikeEdit}>
          {likeState.isLiked && <FontAwesomeIcon icon={likeOn} />}
          {!likeState.isLiked && <FontAwesomeIcon icon={likeOff} />}
        </button>
        <span>{likeState.likeCnt}</span>
      </div>
      <div className="head-report">
        <FontAwesomeIcon icon={faFlag} />
        <button onClick={() => setReportModalOpen(true)}>장소 오류 제보하기</button>
      </div>
    </InfoUpvoteReport>
  )
}

export const MemoUpvoteReport = React.memo(UpvoteReport)

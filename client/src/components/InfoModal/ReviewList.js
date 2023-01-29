import React, { useState } from 'react'
import { toast } from 'react-toastify'

import Loading from '../Loading/Loading'
import { InfoReviewList, List, Item } from './ReviewListStyle'
import { MemoPagination } from '../../components/Pagination/Pagination'
import { amountDisplay, dateConvert } from '../../utils/common'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'
import { useGetReviewListById } from '../../query/review'
import * as reviewApi from '../../api/review'
import { getLoginInfo } from '../../api/login'
import { EMOJI_LIST } from '../../utils/const'

const ReviewList = ({ pId, page, goPage, modifiedReviewId, reloadReviewList }) => {
  // console.log('-- (4)ReviewList Render --')

  const loginMemberId = getLoginInfo().id
  const listSize = 8

  // state, hook
  const [confirmModal, setConfirmModal] = useState({})

  // handle
  const handleReviewDel = (e, confirm) => {
    const reviewId = Number(e.target.value.split('|')[0])
    const memberId = Number(e.target.value.split('|')[1])

    if (loginMemberId !== memberId) return toast.warning('삭제권한이 없습니다.')
    if (loginMemberId === memberId) {
      if (!confirm) {
        return setConfirmModal({
          msg: '정말 삭제하시겠습니까?',
          fnConfirm: () => handleReviewDel(e, true),
        })
      }

      // db delete
      reviewApi.deleteReviewInfo(pId, reviewId).then(data => {
        reloadReviewList(-reviewId)
      })
    }
  }

  const handlePageMove = e => {
    goPage(Number(e.target.value))
  }

  const confirmModalClose = () => {
    setConfirmModal({})
  }

  // fetch data
  const { isLoading, isFetching, isError, error, data } = useGetReviewListById(pId, page, listSize, modifiedReviewId)
  if (isLoading || isFetching) return <Loading />
  if (isError) return toast.error(error.message)
  if (!data) return null
  const { reviewList, totalElements, totalPages } = data

  return (
    <InfoReviewList>
      <ConfirmModal
        msg={confirmModal.msg}
        fnCancel={confirmModalClose}
        fnConfirm={confirmModal.fnConfirm}
        position={confirmModal.position}
      />
      <h3>
        이 장소에 대한 리뷰 <span className="review-cnt">{amountDisplay(totalElements)}</span>개
      </h3>
      <List>
        {reviewList.map(item => (
          <Item key={item.reviewId} isModifyed={modifiedReviewId === item.reviewId}>
            <span className="review-emoji">
              <img
                src={EMOJI_LIST.find(el => el.id === item.emojiId).icon}
                alt={EMOJI_LIST.find(el => el.id === item.emojiId).alt}
              />
            </span>
            <p className="review-comment">{item.content}</p>
            <span className="review-date">{dateConvert(item.createdAt)}</span>
            {loginMemberId === item.memberId && (
              <button className="review-del-btn" value={`${item.reviewId}|${item.memberId}`} onClick={handleReviewDel}>
                ×
              </button>
            )}
          </Item>
        ))}
      </List>
      <MemoPagination page={page} totalPages={totalPages} movePage={handlePageMove} />
    </InfoReviewList>
  )
}

export const MemoReviewList = React.memo(ReviewList)

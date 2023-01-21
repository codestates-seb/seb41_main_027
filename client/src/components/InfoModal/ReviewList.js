import React from 'react'
import { toast } from 'react-toastify'
import Loading from '../Loading/Loading'
import useMoveScroll from '../../hooks/useMoveScroll'
import { useGetReviewListById } from '../../query/review'
import { EMOJI_LIST } from '../../utils/const'
import { AmountDisplay, DateConvert } from '../../utils/common'

const ReviewList = ({ pId, page, goPage, addReviewId }) => {
  // console.log('-- (4)ReviewList Render --')

  // hook
  const { element: listTop, onMoveToElement } = useMoveScroll()

  // fetch data
  const query = useGetReviewListById(pId, page, addReviewId)
  if (query.isLoading) return <Loading />
  if (query.isError) return toast.error(query.error.message)
  const { reviewList, totalElements, totalPages } = query.data
  const list = reviewList || []

  // handle
  const handleReviewDel = e => {
    const delReviewId = e.target.value
    toast.info(`delete reviewId=${delReviewId}`)
  }

  const handlePageMove = e => {
    goPage(Number(e.target.value))
    onMoveToElement()
  }

  // pagination
  const pageCnt = 5
  const pageBlock = Math.ceil(page / pageCnt)
  const isNextBlcok = totalPages > pageBlock * pageCnt
  const isPrevBlock = page > pageCnt
  const sPage = (pageBlock - 1) * pageCnt + 1
  const ePage = sPage + pageCnt - 1 > totalPages ? totalPages : sPage + pageCnt - 1
  const pageList = new Array(ePage - sPage + 1).fill().map((el, idx) => sPage + idx)

  return (
    <>
      <h3 ref={listTop}>
        이 장소에 대한 리뷰 <span className="review-cnt">{AmountDisplay(totalElements)}</span>개
      </h3>
      <ul className="review-list">
        {list.map(item => (
          <li key={item.reviewId} className={addReviewId === item.reviewId ? 'ani-border-twinkle' : ''}>
            <span className="review-emoji">{EMOJI_LIST.find(el => el.id === item.emojiId).emoji}</span>
            <p className="review-comment">{item.content}</p>
            <span className="review-date">{DateConvert(item.createdAt)}</span>
            <button className="review-del-btn" value={item.reviewId} onClick={handleReviewDel}>
              ×
            </button>
          </li>
        ))}
      </ul>
      <div className="list-pagination">
        {isPrevBlock && <button>&lt;</button>}
        {pageList.map(pageNum => (
          <button key={pageNum} className={page === pageNum ? 'selected' : ''} value={pageNum} onClick={handlePageMove}>
            {pageNum}
          </button>
        ))}
        {isNextBlcok && <button>&gt;</button>}
      </div>
    </>
  )
}

export const MemoReviewList = React.memo(ReviewList)

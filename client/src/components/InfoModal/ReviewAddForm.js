import React, { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'

import { InfoReviewAddForm, EmojiBox } from './ReviewAddFormStyle'
import useMoveScroll from '../../hooks/useMoveScroll'
import * as review from '../../api/review'
import { getLoginInfo } from '../../api/login'
import { EMOJI_LIST } from '../../utils/const'

const ReviewAddForm = ({ pId, reloadReviewList }) => {
  // console.log('-- (6)ReviewAddForm Render --')

  const loginMemberId = getLoginInfo().id

  // state, hook
  const refComment = useRef(null)
  const navigate = useNavigate()
  const [selectedEmoji, setSelectedEmoji] = useState(null)
  const { element: formTop, onMoveToElement } = useMoveScroll()

  // emoji list setting
  const emojiList = []
  emojiList.push(EMOJI_LIST.filter((el, idx) => idx < 5))
  emojiList.push(EMOJI_LIST.filter((el, idx) => idx >= 5))

  // handle
  const handleChangeEmoji = emojiId => {
    setSelectedEmoji(emojiId)
    onMoveToElement()
  }

  const handleSubmitReview = e => {
    e.preventDefault()
    const comment = e.target.comment_text.value

    // db create
    const body = {
      placeId: Number(pId),
      content: comment,
      emojiId: selectedEmoji,
    }
    review.createReviewInfo(body).then(data => {
      reloadReviewList(Number(data.reviewId))
      setSelectedEmoji(null)
      e.target.comment_text.value = ''
    })
  }

  useEffect(() => {
    if (selectedEmoji && refComment.current) refComment.current.focus()
  }, [selectedEmoji])

  return (
    <InfoReviewAddForm onSubmit={handleSubmitReview}>
      <section ref={formTop} className="review-add-emoji-box">
        <h3>이모지를 클릭해 리뷰를 남겨보세요!</h3>
        {emojiList.map((gItem, gIdx) => (
          <div className="emoji-list" key={gIdx}>
            {gItem.map(item => (
              <EmojiBox key={item.id} selected={item.id === selectedEmoji} onClick={() => handleChangeEmoji(item.id)}>
                <img src={item.icon} alt={item.alt} />
              </EmojiBox>
            ))}
          </div>
        ))}
      </section>
      {selectedEmoji && (
        <section className="review-add-comment ani-fadein">
          {!loginMemberId && (
            <div className="login-comment">
              <p>리뷰 등록은 로그인 후 이용할 수 있어요. </p>
              <button type="button" onClick={() => navigate('/signin')}>
                로그인 페이지로 이동
              </button>
            </div>
          )}
          {loginMemberId && (
            <>
              <textarea
                ref={refComment}
                className="comment_text"
                name="comment_text"
                maxLength={40}
                placeholder="여기에 리뷰를 입력해 주세요."
                required
              />
              <button type="submit" className="comment-save-btn" disabled={false}>
                저장하기
              </button>
            </>
          )}
        </section>
      )}
    </InfoReviewAddForm>
  )
}

export const MemoReviewAddForm = React.memo(ReviewAddForm)

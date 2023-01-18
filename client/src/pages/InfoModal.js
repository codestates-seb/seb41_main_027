import { useNavigate, useParams, Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
  faShareNodes,
  faComment,
  faBookmark as bookmarkOn,
  faHeart as upvoteOn,
  faFlag,
} from '@fortawesome/free-solid-svg-icons'
import { faBookmark as bookmarkOff, faHeart as upvoteOff, faCopy } from '@fortawesome/free-regular-svg-icons'
import {
  ModalDimmed,
  ModalWrapper,
  InfoContent,
  InfoBottom,
  InfoHead,
  InfoBody,
  InfoAbout,
  InfoMapReview,
  InfoReviewList,
  InfoMapReviewAdd,
} from './InfoModalStyle'

const InfoModal = () => {
  console.log('-- InfoModal Render --')
  console.log('url', window.location.href)
  // id check
  const infoId = useParams().infoId
  if (!Number(infoId)) return null

  const navigate = useNavigate()

  const isBookmark = true
  const isUpvote = false

  const emojiList1 = [
    { id: 1, emoji: '😀' },
    { id: 2, emoji: '🤔' },
    { id: 3, emoji: '🙂' },
    { id: 4, emoji: '😍' },
    { id: 5, emoji: '🤥' },
  ]
  const emojiList2 = [
    { id: 6, emoji: '🤩' },
    { id: 7, emoji: '🥹' },
    { id: 8, emoji: '😧' },
    { id: 9, emoji: '😂' },
    { id: 10, emoji: '😆' },
  ]

  const handleModalClose = e => {
    console.log('handleModalClose')
    navigate(-1)
  }

  const handleChangeEmoji = () => {}

  const handleClickKakaoMapMove = () => {
    window.open(`https://map.kakao.com/?itemId=${infoId}`, '_blank')
  }

  return (
    <>
      <ModalDimmed onClick={handleModalClose}>
        <ModalWrapper onClick={e => e.stopPropagation()}>
          <InfoContent>
            <InfoHead>
              <div className="head-category-title">
                <div className="head-title">
                  <span className="category">공방</span>
                  <p>따솝비누공방</p>
                </div>
                <button onClick={handleModalClose}>×</button>
              </div>
              <div className="head-address">
                <p>서울특별시 용산구 서빙고로 17</p>
                <button>
                  <FontAwesomeIcon icon={faCopy} />
                </button>
                <span className="copied">copied</span>
              </div>
              <div className="head-upvote-report">
                <div className="head-upvote">
                  <button>
                    <FontAwesomeIcon icon={isUpvote ? upvoteOn : upvoteOff} />
                  </button>
                  <span>129</span>
                </div>
                <div className="head-report">
                  <FontAwesomeIcon icon={faFlag} />
                  <button>장소 오류 제보하기</button>
                </div>
              </div>
            </InfoHead>
            <InfoBody>
              <InfoAbout>
                <textarea
                  name="about_text"
                  readOnly={true}
                  defaultValue={`친환경 패션잡화, 청소용품 등을 판매합니다.친환경 패션잡화, 청소용품 등을 판매합니다.친환경 패션잡화, 청소용품 등을 판매합니다.친환경 패션잡화, 청소용품 등을 판매합니다.`}
                />
                <div className="body-about-modify">
                  <button>Edit</button>
                  <button>Save</button>
                </div>
              </InfoAbout>
              <InfoMapReview>
                <InfoReviewList>
                  <h3>이 장소에 대한 리뷰</h3>
                  <ul>
                    {[1, 2, 3, 4, 5, 6, 7, 8].map(el => (
                      <li key={el}>
                        <span className="review-emoji">😀</span>
                        <p className="review-comment">원데이 클래스 예약 방법이 간편해서 좋았어요.</p>
                        <button className="review-del-btn">×</button>
                      </li>
                    ))}
                  </ul>
                </InfoReviewList>
                <InfoMapReviewAdd>
                  <section className="map">
                    <h3>여기에 있어요.</h3>
                    <div className="map-static">지도</div>
                  </section>
                  <form className="review-add-form">
                    <section className="review-add-emoji-box">
                      <h3>이모지를 클릭해 리뷰를 남겨보세요!</h3>
                      <div className="emoji-list">
                        {emojiList1.map((item, idx) => (
                          <span className="emoji-item" key={item.id}>
                            <input
                              type="radio"
                              id={`radio${item.id}`}
                              name="emoji_id"
                              value={item.id}
                              onChange={handleChangeEmoji}
                              required
                            />
                            <label htmlFor={`radio${item.id}`}>{item.emoji}</label>
                          </span>
                        ))}
                      </div>
                      <div className="emoji-list">
                        {emojiList2.map((item, idx) => (
                          <span className="emoji-item" key={item.id}>
                            <input
                              type="radio"
                              id={`radio${item.id}`}
                              name="emoji_id"
                              value={item.id}
                              onChange={handleChangeEmoji}
                              required
                            />
                            <label htmlFor={`radio${item.id}`}>{item.emoji}</label>
                          </span>
                        ))}
                      </div>
                    </section>
                    <section className="review-add-comment">
                      <textarea
                        className="comment_text"
                        name="comment_text"
                        placeholder="여기에 리뷰를 입력해 주세요."
                        required
                      />
                      <button type="submit" className="comment-save-btn" disabled={false}>
                        저장하기
                      </button>
                    </section>
                  </form>
                </InfoMapReviewAdd>
              </InfoMapReview>
            </InfoBody>
          </InfoContent>
          <InfoBottom>
            <div className="bottom-wrapper">
              <div className="bottom-share">
                <button className="btn-icon">
                  <FontAwesomeIcon icon={faShareNodes} />
                </button>
                <button className="btn-icon">
                  <FontAwesomeIcon icon={faComment} />
                </button>
              </div>
              <div className="bottom-detail-link">
                <button>카카오 맵으로 자세히 보기</button>
              </div>
              <div className="bottom-bookmark">
                <button className="btn-icon">
                  <FontAwesomeIcon icon={isBookmark ? bookmarkOn : bookmarkOff} />
                </button>
              </div>
            </div>
          </InfoBottom>
        </ModalWrapper>
      </ModalDimmed>
    </>
  )
}

export default InfoModal

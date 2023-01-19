import { useNavigate, useParams } from 'react-router-dom'

import {
  ModalDimmed,
  ModalWrapper,
  InfoContent,
  InfoBottom,
  InfoHead,
  InfoBody,
  InfoMapReview,
  InfoReviewList,
  InfoMapReviewAdd,
} from './InfoModalStyle'
import Loading from '../../components/Loading/Loading'
import {
  MemoTitle,
  MemoUpvoteReport,
  MemoAboutEditForm,
  MemoLocationMap,
  MemoReviewAddForm,
  MemoReviewList,
  MemoShareBookmark,
} from '../../components/InfoModal'
import { toast } from 'react-toastify'
import { getPlaceInfoById } from '../../query'
import { useState } from 'react'

export const InfoModal = () => {
  console.log('-- InfoModal Render --')

  // id check
  const pId = useParams().infoId
  if (!Number(pId)) return null

  // state, hook
  const navigate = useNavigate()
  const [page, setPage] = useState(1) // review list
  const [addReviewId, setAddReviewId] = useState(0) // review list

  // fetch data
  const query = getPlaceInfoById(pId)
  if (query.isLoading) return <Loading />
  if (query.isError) return toast.error(query.error.message)
  const item = query.data

  // dev exception(after delete)
  if (item.placeLikeCount === undefined) item.placeLikeCount = 149
  if (item.isLiked === undefined) item.isLiked = false
  if (item.latitude === '0') item.latitude = '37.5160953'
  if (item.longitude === '0') item.longitude = '126.8906455'

  // func
  const goPage = pageNum => {
    setAddReviewId(0)
    setPage(pageNum)
  }

  // review create > review list call
  const createdReview = newReviewId => {
    setAddReviewId(newReviewId)
    setPage(1)
  }

  return (
    <>
      <ModalDimmed onClick={() => navigate(-1)}>
        <ModalWrapper onClick={e => e.stopPropagation()}>
          <InfoContent>
            <InfoHead>
              <MemoTitle item={item} />
              <MemoUpvoteReport item={item} />
            </InfoHead>
            <InfoBody>
              <MemoAboutEditForm item={item} />
              <InfoMapReview>
                <InfoReviewList>
                  <MemoReviewList pId={pId} page={page} goPage={goPage} addReviewId={addReviewId} />
                </InfoReviewList>
                <InfoMapReviewAdd>
                  <MemoLocationMap title={item.name} lat={item.latitude} lng={item.longitude} />
                  <MemoReviewAddForm pId={pId} createdReview={createdReview} />
                </InfoMapReviewAdd>
              </InfoMapReview>
            </InfoBody>
          </InfoContent>
          <InfoBottom>
            <MemoShareBookmark item={item} />
          </InfoBottom>
        </ModalWrapper>
      </ModalDimmed>
    </>
  )
}

export default InfoModal

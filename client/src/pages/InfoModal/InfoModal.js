import React, { useCallback, useState } from 'react'
import { toast } from 'react-toastify'
import { useNavigate, useParams } from 'react-router-dom'
import { useQueryClient } from '@tanstack/react-query'

import {
  ModalDimmed,
  ModalWrapper,
  InfoContent,
  InfoBottom,
  InfoHead,
  InfoBody,
  InfoMapReview,
  InfoReviewListWrap,
  InfoMapReviewAdd,
} from './InfoModalStyle'
import {
  MemoTitle,
  MemoUpvoteReport,
  MemoAboutEditForm,
  MemoLocationMap,
  MemoReviewAddForm,
  MemoReviewList,
  MemoShareBookmark,
} from '../../components/InfoModal'
import Loading from '../../components/Loading/Loading'
import useMoveScroll from '../../hooks/useMoveScroll'
import { useGetPlaceInfoById } from '../../query/place'

export const InfoModal = () => {
  // console.log('-- InfoModal Render --')

  // parameter, id check
  const pId = useParams().infoId || useParams().placeId
  if (!Number(pId)) return null

  // state, hook
  const navigate = useNavigate()
  const queryClient = useQueryClient()
  const { element: reviewListTop, onMoveToElement } = useMoveScroll()
  const [page, setPage] = useState(1) // review list
  const [modifiedReviewId, setModifiedReviewId] = useState(0) // review list

  // func
  const goPage = useCallback(
    pageNum => {
      if (reviewListTop.current) onMoveToElement()
      setModifiedReviewId(0)
      setPage(pageNum)
    },
    [pId],
  )

  // review create > review list call
  const reloadReviewList = useCallback(
    reviewId => {
      if (reviewListTop.current) onMoveToElement()
      setModifiedReviewId(reviewId)
      setPage(1)
    },
    [pId],
  )

  const queryRefresh = useCallback(() => {
    queryClient.invalidateQueries({ queryKey: ['getPlaceInfoById'] })
  }, [pId])

  const handleModalClose = useCallback(() => {
    const bgLocation = location.state && location.state.bgLocation
    if (bgLocation) navigate(bgLocation.pathname)
    if (!bgLocation) navigate('/')
  }, [pId])

  // fetch data
  const { isLoading, isFetching, isError, error, data } = useGetPlaceInfoById(pId)
  if (isLoading || isFetching) return <Loading />
  if (isError) return toast.error(error.message)
  if (!data) return null
  const item = data

  return (
    <ModalDimmed onClick={handleModalClose}>
      <ModalWrapper onClick={e => e.stopPropagation()}>
        <InfoContent>
          <InfoHead>
            <MemoTitle item={item} modalClose={handleModalClose} />
            <MemoUpvoteReport item={item} queryRefresh={queryRefresh} />
          </InfoHead>
          <InfoBody>
            <MemoAboutEditForm item={item} />
            <InfoMapReview>
              <InfoReviewListWrap ref={reviewListTop}>
                <MemoReviewList
                  pId={pId}
                  page={page}
                  goPage={goPage}
                  modifiedReviewId={modifiedReviewId}
                  reloadReviewList={reloadReviewList}
                />
              </InfoReviewListWrap>
              <InfoMapReviewAdd>
                <MemoLocationMap item={item} />
                <MemoReviewAddForm pId={pId} reloadReviewList={reloadReviewList} />
              </InfoMapReviewAdd>
            </InfoMapReview>
          </InfoBody>
        </InfoContent>
        <InfoBottom>
          <MemoShareBookmark item={item} queryRefresh={queryRefresh} />
        </InfoBottom>
      </ModalWrapper>
    </ModalDimmed>
  )
}

export default InfoModal

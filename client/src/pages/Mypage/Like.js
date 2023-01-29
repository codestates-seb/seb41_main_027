import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrashCan } from '@fortawesome/free-solid-svg-icons'
import { toast } from 'react-toastify'
import { useQueryClient } from '@tanstack/react-query'

import Loading from '../../components/Loading/Loading'
import { ContentWrap, List, Item, FieldName, DelButton, Category, PlaceName, Address } from './BookmarkStyle'
import { MemoPagination } from '../../components/Pagination/Pagination'
import * as likeApi from '../../api/like'
import { useGetLikeList } from '../../query/like'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'

const Bookmark = () => {
  const listSize = 8

  // hook, state
  const navigate = useNavigate()
  const [page, setPage] = useState(1)
  const [confirmModal, setConfirmModal] = useState({})
  const queryClient = useQueryClient()

  // handle
  const handleBookmarkDel = (pId, confirm) => {
    if (!confirm) {
      return setConfirmModal({
        msg: '정말 삭제하시겠습니까?',
        fnConfirm: () => handleBookmarkDel(pId, true),
      })
    }

    // db delete
    likeApi.updateLike(pId, false).then(data => {
      setPage(1)
      queryClient.invalidateQueries({ queryKey: ['getLikeList'] })
    })
  }

  const movePage = e => {
    setPage(Number(e.target.value))
  }

  // fetch data
  const { isLoading, isFetching, isError, error, data } = useGetLikeList(page, listSize)
  if (isLoading || isFetching) return <Loading />
  if (isError) return toast.error(error.message)
  if (!data) return null
  const { placeList, totalElements, totalPages } = data

  return (
    <ContentWrap>
      <ConfirmModal msg={confirmModal.msg} fnCancel={() => setConfirmModal({})} fnConfirm={confirmModal.fnConfirm} />
      <List>
        {placeList.length === 0 && <Item>등록된 추천장소가 없습니다.</Item>}
        {placeList.map(item => (
          <Item key={item.placeId}>
            <FieldName>
              <Category>{item.category}</Category>
              <PlaceName>{item.name}</PlaceName>
              <Address>{item.address}</Address>
            </FieldName>
            <DelButton
              type="button"
              name="place_id"
              value={item.placeId}
              onClick={() => handleBookmarkDel(item.placeId)}
            >
              <FontAwesomeIcon icon={faTrashCan} />
            </DelButton>
          </Item>
        ))}
        <MemoPagination page={page} totalPages={totalPages} movePage={movePage} />
      </List>
    </ContentWrap>
  )
}

export default Bookmark

import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrashCan } from '@fortawesome/free-solid-svg-icons'
import { toast } from 'react-toastify'
import { useQueryClient } from '@tanstack/react-query'

import Loading from '../../components/Loading/Loading'
import { ContentWrap, Summary, List, Item, FieldName, DelButton, Head, Title, Tail, Span } from './AdminStyle'
import { MemoPagination } from '../../components/Pagination/Pagination'
import * as placeApi from '../../api/place'
import { useGetPlace } from '../../query/place'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'

const Bookmark = () => {
  // hook, state
  const navigate = useNavigate()
  const [page, setPage] = useState(1)
  const [confirmModal, setConfirmModal] = useState({})
  const queryClient = useQueryClient()

  // handle
  const handlePlaceDel = (pId, confirm) => {
    if (!confirm) {
      return setConfirmModal({
        msg: '정말 삭제하시겠습니까?',
        fnConfirm: () => handlePlaceDel(pId, true),
      })
    }

    // db delete
    placeApi.deletePlace(pId).then(data => {
      setPage(1)
      queryClient.invalidateQueries({ queryKey: ['getPlace'] })
    })
  }

  const movePage = e => {
    setPage(Number(e.target.value))
  }

  // fetch data : useGetPlace(sort, categoryId, page)
  const { isLoading, isFetching, isError, error, data } = useGetPlace('time', '', page)
  if (isLoading || isFetching) return <Loading />
  if (isError) return toast.error(error.message)
  if (!data) return null
  const { placeList, totalElements, totalPages } = data

  return (
    <ContentWrap>
      <ConfirmModal msg={confirmModal.msg} fnCancel={() => setConfirmModal({})} fnConfirm={confirmModal.fnConfirm} />
      <Summary>
        <Span bgColor="#ccc">장소 아이디</Span>
        <Span bgColor="#aaa">회원 아이디</Span>
        Total <strong>{totalElements}</strong>개
      </Summary>
      <List>
        {placeList.length === 0 && <Item>등록된 장소가 없습니다.</Item>}
        {placeList.map(item => (
          <Item key={item.placeId}>
            <FieldName>
              <Head>{item.category}</Head>
              <Title>
                {item.name}
                <Span bgColor="#ccc">{item.placeId}</Span>
                <Span bgColor="#aaa">{item.memberId}</Span>
              </Title>
              <Tail>{new Date(item.createdAt).toLocaleString()}</Tail>
            </FieldName>
            <DelButton type="button" name="place_id" value={item.placeId} onClick={() => handlePlaceDel(item.placeId)}>
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

import { useNavigate } from 'react-router-dom'
import * as mailApi from '../../api/mail'

import {
  ModalDimmed,
  ModalWrapper,
  EditForm,
  Item,
  FieldName,
  Input,
  Textarea,
  ButtonWrap,
  Button,
} from './ReportModalStyle'
import Loading from '../../components/Loading/Loading'
import { useState } from 'react'
import { faTruckMonster } from '@fortawesome/free-solid-svg-icons'

const ReportModal = ({ subject, modalClose }) => {
  const placeId = subject ? subject[0] : null
  const placeName = subject ? subject[1] : null

  // hook, state
  const navigate = useNavigate()
  const [isLoading, setIsLoading] = useState(false)

  // handle
  const handleSubmit = e => {
    e.preventDefault()

    const body = {}
    body.title = subject ? `${placeId}. ${placeName}` : e.target.title.value
    body.content = e.target.content.value

    const callback = () => {
      setIsLoading(false)
      modalClose()
    }

    setIsLoading(true)
    mailApi.sendReportMail(body, callback)
  }
  return (
    <ModalDimmed onClick={modalClose}>
      {isLoading && <Loading />}
      <ModalWrapper onClick={e => e.stopPropagation()}>
        <EditForm onSubmit={handleSubmit}>
          <Item>
            {subject && (
              <>
                <FieldName>장소</FieldName>
                {placeName}
              </>
            )}
            {!subject && (
              <>
                <FieldName>제목</FieldName>
                <Input type="text" name="title" placeholder="제목을 입력해 주세요." required />
              </>
            )}
          </Item>
          <Item>
            <FieldName>내용</FieldName>
            <Textarea type="text" name="content" placeholder="제보 내용을 입력해 주세요." required />
          </Item>
          <ButtonWrap>
            <Button type="button" cancel onClick={modalClose}>
              취소하기
            </Button>
            <Button type="submit">보내기</Button>
          </ButtonWrap>
        </EditForm>
      </ModalWrapper>
    </ModalDimmed>
  )
}

export default ReportModal

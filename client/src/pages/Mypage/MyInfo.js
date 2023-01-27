import { useRef, useState } from 'react'
import { toast } from 'react-toastify'
import { useLocation, useNavigate } from 'react-router-dom'
import { useQueryClient } from '@tanstack/react-query'

import Loading from '../../components/Loading/Loading'
import {
  ContentWrap,
  Item,
  FieldName,
  FieldText,
  ValidText,
  Input,
  EditForm,
  Button,
  WithdrawalBtn,
} from './MyInfoStyle'
import { MEMBER_NICKNAME_REGEX } from '../../utils/const'
import * as memberApi from '../../api/member'
import { useGetMemberInfoById } from '../../query/member'
import { ConfirmModal } from '../../components/Modal/ConfirmModal'
import * as loginApi from '../../api/login'

const MyInfo = () => {
  const loginMemberId = loginApi.getLoginInfo().id

  // hook
  const refNickName = useRef(null)
  const navigate = useNavigate()
  const location = useLocation()
  const queryClient = useQueryClient()

  // state
  const [isEdit, setIsEdit] = useState(false)
  const [validation, setValidation] = useState({ isValid: true, text: '' })
  const [confirmModal, setConfirmModal] = useState({})

  // handle
  const handleChangeNicname = e => {
    const nickNameText = e.target.value
    let checkedVal = { isValid: true, text: '✅' }

    if (!MEMBER_NICKNAME_REGEX.test(nickNameText)) {
      checkedVal.isValid = false
      checkedVal.text = '2~ 12자, 영문자/숫자/한글'
    }

    setValidation(checkedVal)
  }

  const handleNicknameEdit = e => {
    e.preventDefault()

    const changeNickname = refNickName.current.value
    const initNickname = refNickName.current.defaultValue

    if (!validation.isValid) {
      toast.info('닉네임 형식을 확인해 주세요.')
      changeEditMode(true)
      return
    } else if (changeNickname === initNickname) {
      toast.info('변경된 내용이 없습니다.')
      changeEditMode(true)
      return
    }

    // db update
    memberApi.updateMemberInfo({ nickName: changeNickname }).then(data => {
      loginApi.resetNickName(data.nickName)
      changeEditMode(false)
      queryClient.invalidateQueries({ queryKey: ['getMemberInfoById'] })
      navigate(location.pathname, { replace: true })
    })
  }

  const handleMemberDel = (e, confirm) => {
    if (!confirm) {
      return setConfirmModal({
        msg: '정말 탈퇴하시겠습니까?',
        fnConfirm: () => handleMemberDel(e, true),
      })
    }

    // db delete
    memberApi.deleteMember()
    //navigate('/logout')
  }

  const changeEditMode = editMode => {
    setIsEdit(editMode)

    const eleNickname = refNickName.current

    if (editMode) {
      eleNickname.focus()
      eleNickname.readOnly = false
    }
    if (!editMode) {
      eleNickname.value = eleNickname.defaultValue
      eleNickname.readOnly = true
      setValidation({ isValid: true, text: '' })
    }
  }

  const handlePwdEditModalOpen = () => {
    navigate('/mypage/myinfo/pwdedit', { state: { bgLocation: location } })
  }

  // fetch data
  const { isLoading, isFetching, isError, error, data } = useGetMemberInfoById(loginMemberId)
  if (isLoading || isFetching) return <Loading />
  if (isError) return toast.error(error.message)
  if (!data) return null

  return (
    <ContentWrap>
      <ConfirmModal msg={confirmModal.msg} fnCancel={() => setConfirmModal({})} fnConfirm={confirmModal.fnConfirm} />
      <Item>
        <FieldName>닉네임</FieldName>
        <FieldText>
          <Input
            ref={refNickName}
            name="nickname"
            defaultValue={data.nickName}
            onChange={handleChangeNicname}
            maxLength={12}
            placeholder="닉네임을 입력해 주세요."
            readOnly
            required
          />
          <ValidText isValid={validation.isValid}>{validation.text}</ValidText>
        </FieldText>
        <EditForm onSubmit={handleNicknameEdit}>
          {isEdit && (
            <>
              <Button type="button" cancel onClick={() => changeEditMode(false)}>
                취소
              </Button>
              <Button type="submit">저장하기</Button>
            </>
          )}
          {!isEdit && (
            <Button type="button" onClick={() => changeEditMode(true)}>
              변경하기
            </Button>
          )}
        </EditForm>
      </Item>
      <Item>
        <FieldName>비밀번호</FieldName>
        <FieldText>********</FieldText>
        <Button onClick={handlePwdEditModalOpen}>변경하기</Button>
      </Item>
      <WithdrawalBtn type="button" onClick={handleMemberDel}>
        회원 탈퇴
      </WithdrawalBtn>
    </ContentWrap>
  )
}

export default MyInfo

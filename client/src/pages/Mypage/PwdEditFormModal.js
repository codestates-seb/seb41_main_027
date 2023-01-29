import { useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'

import {
  ModalDimmed,
  ModalWrapper,
  EditForm,
  Item,
  FieldName,
  Input,
  ValidText,
  ButtonWrap,
  Button,
} from './PwdEditFormModalStyle'
import { MEMBER_PWD_REGEX } from '../../utils/const'
import * as memberApi from '../../api/member'
import { getLoginInfo } from '../../api/login'

const PwdEditFormModal = () => {
  const loginMemberId = getLoginInfo().id

  // login check
  if (!loginMemberId) return null

  // hook, state
  const navigate = useNavigate()
  const refPwd = useRef()
  const refNewPwd = useRef()
  const [validPwd, setValidPwd] = useState({ isValid: true, text: '' })
  const [validNewPwd, setValidNewPwd] = useState({ isValid: true, text: '' })

  // handle
  const handleSubmit = e => {
    e.preventDefault()

    const pwd = refPwd.current
    const newPwd = refNewPwd.current

    if (!validPwd.isValid) {
      pwd.focus()
      return toast.info('비밀번호 형식을 확인해 주세요.')
    } else if (!validNewPwd.isValid) {
      newPwd.focus()
      return toast.info('새 비밀번호 형식을 확인해 주세요.')
    }

    // db update
    memberApi.updateMemberInfo({ password: pwd.value })
    handleModalClose()
  }

  const handleChangePassword = e => {
    const pwd = refPwd.current
    const newPwd = refNewPwd.current

    const target = pwd === e.target ? pwd : newPwd
    const isValidCheck = MEMBER_PWD_REGEX.test(target.value)
    if (isValidCheck) {
      setValidPassword(target, true, '올바른 비밀번호입니다.')
    } else if (!isValidCheck) {
      setValidPassword(target, false, '8자 이상 16자 이하로 입력해 주세요.')
    }

    if (pwd.value && newPwd.value) {
      if (validPwd.isValid && pwd.value === newPwd.value) setValidPassword(newPwd, true, '비밀번호가 일치합니다.')
      if (validPwd.isValid && pwd.value !== newPwd.value)
        setValidPassword(newPwd, false, '비밀번호가 일치하지 않습니다.')
    }
  }

  const setValidPassword = (target, isValid, text) => {
    if (target.name === 'password') setValidPwd({ isValid, text })
    else if (target.name === 'new_password') setValidNewPwd({ isValid, text })
  }

  const handleModalClose = () => {
    navigate(-1)
  }

  return (
    <ModalDimmed onClick={handleModalClose}>
      <ModalWrapper onClick={e => e.stopPropagation()}>
        <EditForm onSubmit={handleSubmit}>
          <Item>
            <FieldName>비밀번호</FieldName>
            <Input
              ref={refPwd}
              type="password"
              name="password"
              onChange={handleChangePassword}
              maxLength={20}
              placeholder="비밀번호를 입력해 주세요."
              isValid={validPwd.isValid}
              autoComplete="off"
              required
            />
            <ValidText isValid={validPwd.isValid}>{validPwd.text}</ValidText>
          </Item>
          <Item>
            <FieldName>비밀번호 재확인</FieldName>
            <Input
              ref={refNewPwd}
              type="password"
              name="new_password"
              onChange={handleChangePassword}
              maxLength={20}
              placeholder="새 비밀번호를 입력해 주세요."
              isValid={validNewPwd.isValid}
              autoComplete="off"
              required
            />
            <ValidText isValid={validNewPwd.isValid}>{validNewPwd.text}</ValidText>
          </Item>
          <ButtonWrap>
            <Button type="button" cancel onClick={handleModalClose}>
              취소하기
            </Button>
            <Button type="submit">저장하기</Button>
          </ButtonWrap>
        </EditForm>
      </ModalWrapper>
    </ModalDimmed>
  )
}

export default PwdEditFormModal

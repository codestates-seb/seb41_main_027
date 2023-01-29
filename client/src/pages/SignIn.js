import axios from 'axios'
import { useState, useEffect, useRef } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
// import { useForm } from 'react-hook-form';

import { API_LOGIN_ENDPOINT, MEMBER_PWD_REGEX } from '../utils/const'
import { customAxios } from '../utils/customAxios'
import { setLoginInfo } from '../api/login'

import styled from 'styled-components'
import Logo from '../assets/LogoTypeSignature.svg'

//💄 Demo Styles --------------------------------
const Wrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`
const Container = styled.div`
  width: 418px;
  height: 552px;
  padding: 32px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  background: #ffffff;
  border: 4px solid #f2f3f5;
  box-shadow: 0px 2px 16px rgba(14, 0, 31, 0.12);
  border-radius: 32px;

  .cmm-form {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    form {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      /* gap: 8px; */
      width: 100%;
      legend {
        margin-bottom: 8px;
        color: #363738;
        font-size: 24px;
        font-weight: 600;
      }
      .sign-about {
        margin: 8px 0 16px 0;
        gap: 8px;
        font-weight: 300;
        font-size: 12px;
        & a {
          color: #000;
          padding-left: 8px;
          font-weight: 600;
        }
      }
    }
  }

  button {
    cursor: pointer;
    width: 100%;
    height: 48px;
    margin-top: 32px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    font-size: 20px;
    font-weight: 400;
    background: #d2d5e1;
    border-radius: 12px;
    letter-spacing: -0.1px;
    /* gap: 4px; btn 및 ico 추가시 */
  }
  .btn-disabled {
    /* background: #222a33; */
  }
  .btn-submit {
    font-weight: 500;
    background: #384452;
  }

  .input-wrap {
    position: relative;
    margin-bottom: 8px;
    box-sizing: border-box;
    width: 100%;
    height: 48px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    padding: 4px 16px;
    width: 100%;
    height: 48px;
    color: #384452;
    font-size: 14px;
    border: 1px solid #d2d5e1;
    background-color: #fff;
    border-radius: 12px;
    input::placeholder,
    input::-webkit-input-placeholder {
      color: rgb(0 0 0 / 40%);
      font-weight: 400;
      font-size: 14px;
      line-height: 14px;
    }
    > label {
      position: absolute;
      width: 32px;
      font-size: 18px;
      top: 14px;
      right: 8px;
      text-align: center;
    }
  }

  // 유효성 에러메세지 비노출시 demo
  .offscreen {
    position: absolute;
    left: -9999px;
  }
  // 유효성 에러메세지 노출시 demo
  .instructions {
    position: relative;
    width: 100%;
    padding: 0 8px 16px;
    box-sizing: border-box;
    color: #ff2c2c;
    font-size: 14px;
    line-height: 16px;
    font-weight: 400;
    border-radius: 4px;
  }

  .hide {
    display: none;
  }

  .valid {
    color: #13c57c;
  }

  .invalid {
    color: #ff2c2c;
  }

  .errmsg {
    width: 100%;
    margin: 8px;
    padding: 4px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #e90000;
    font-size: 15px;
    font-weight: 40;
    line-height: 22px;
    border-radius: 8px;
    border: 0.2px solid #fbcdcd;
    background: #ffeded;
  }

  a,
  a:visited {
    margin-top: 16px;
    color: blue;
    font-size: 12px;
  }

  .line {
    display: inline-block;
  }

  .errMessageWrap > div {
    margin-top: 8px;
    color: #ef0000;
    font-size: 12px;
  }
`

// 🤖  Regex set ----------------------------------------
const EMAIL_REGEX = new RegExp('[a-z0-9]+@[a-z]+.[a-z]{2,3}')

const SignIn = () => {
  localStorage.clear()

  const userRef = useRef()
  const errRef = useRef()
  const location = useLocation()
  const callbackUrl = location.state?.callbackUrl || '/'
  const navigate = useNavigate()

  const [email, setEmail] = useState('')
  const [validEmail, setValidEmail] = useState(false)
  const [emailFocus, setEmailFocus] = useState(false)

  const [password, setpassword] = useState('')
  const [validPassword, setValidPassword] = useState(false)
  const [passwordFocus, setPasswordFocus] = useState(false)

  const [errMsg, setErrMsg] = useState('')
  const [success, setSuccess] = useState(false)

  useEffect(() => {
    userRef.current.focus()
  }, [])

  useEffect(() => {
    setValidEmail(EMAIL_REGEX.test(email))
  }, [email])

  useEffect(() => {
    setValidPassword(MEMBER_PWD_REGEX.test(password))
  }, [password])

  useEffect(() => {
    setErrMsg('')
  }, [email, password])

  const handleSubmit = async e => {
    e.preventDefault()
    // 버튼 활성화 되었을 경우 (js hack)
    const v1 = EMAIL_REGEX.test(email)
    const v2 = MEMBER_PWD_REGEX.test(password)
    if (!v1 || !v2) {
      setErrMsg('입력된 정보를 다시 확인해주세요.')
      return
    }
    try {
      const response = await customAxios.post(API_LOGIN_ENDPOINT, { username: email, password }).then(response => {
        // localstorage login info save
        const { memberId, nickName } = response.data
        const { authorization, refresh } = response.headers
        setLoginInfo(memberId, nickName, authorization, refresh)
        navigate(callbackUrl, { replace: true })
        // 상태 및 인풋 날리기 clean!
        // 입력을 위한 대한 속성 값 필요
        setEmail('')
        setpassword('')
      })
    } catch (err) {
      // 1. DB에 계정이 존재하지 않음: 404
      // 2. 이미 등록되어 있는 계정인데 + 비밀번호가 틀렸다면: 400
      console.log('err', err)
      if (!err?.response) {
        setErrMsg(
          <p>
            서버의 응답이 없어요. <br />
            새로고침 후 다시 시도해주세요.😭
          </p>,
        )
      } else if (err.response?.status === 400) {
        setErrMsg(
          <p>
            비밀번호가 맞지 않아요.
            <br />
            다시 한 번 확인 해주세요. 🥹
          </p>,
        )
      } else if (err.response?.status === 404) {
        setErrMsg(
          <p>
            존재하지 않는 계정이에요.
            <br /> 이메일을 다시 한 번 확인 해주세요. 🥹
          </p>,
        )
      } else {
        setErrMsg(
          <p>
            로그인에 실패했어요.
            <br />
            새로고침 후 다시 시도해주세요.😭
          </p>,
        )
      }
      errRef.current.focus()
    }
  }

  return (
    <Wrapper>
      <h1>
        <img src={Logo} className="Logo" alt="EcoGreenSeoul Logo TypeSignature" />
      </h1>
      <Container>
        <section className="cmm-form">
          <form onSubmit={handleSubmit}>
            <legend>로그인</legend>
            <p className="sign-about">
              회원가입을 하신 적이 없으신가요?
              <span className="line">
                <Link to="/signup">회원가입 하기</Link>
              </span>
            </p>
            <div className="input-wrap">
              <input
                placeholder="이메일을 입력해주세요."
                type="text"
                id="email"
                ref={userRef}
                autoComplete="off"
                onChange={e => setEmail(e.target.value)}
                value={email}
                required
                aria-invalid={validEmail ? 'false' : 'true'}
                aria-describedby="uidnote"
                onFocus={() => setEmailFocus(true)}
                onBlur={() => setEmailFocus(false)}
              />
            </div>
            <p id="uidnote" className={emailFocus && email && !validEmail ? 'instructions' : 'offscreen'}>
              이메일 형태로 입력해주세요.
            </p>
            <div className="input-wrap">
              <input
                placeholder="비밀번호를 입력해주세요."
                type="password"
                id="password"
                onChange={e => setpassword(e.target.value)}
                value={password}
                required
                aria-invalid={validPassword ? 'false' : 'true'}
                aria-describedby="passwordnote"
                onFocus={() => setPasswordFocus(true)}
                onBlur={() => setPasswordFocus(false)}
              />
            </div>
            <p id="passwordnote" className={passwordFocus && !validPassword ? 'instructions' : 'offscreen'}>
              8글자에서 24글자 이내로 입력해주세요.
            </p>
            <button className={!validEmail || !validPassword ? 'btn-disabled' : 'btn-submit'}>로그인</button>
            <div ref={errRef} className={errMsg ? 'errmsg' : 'offscreen'} aria-live="assertive">
              {errMsg}
            </div>
          </form>
        </section>
      </Container>
    </Wrapper>
  )
}

export default SignIn

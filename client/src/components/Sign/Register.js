import axios from '../../api/sign'
import { useRef, useState, useEffect, useCallback } from 'react'
import { Link } from 'react-router-dom'

// import '../Sign/Register.css'
import styled from 'styled-components'
import { faCircleCheck, faCircleXmark } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

// import { vaildEmail, createMember } from '../../api/member'

//💄 Demo Styles --------------------------------
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
        & > a {
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
    display: none;
  }
  // 유효성 에러메세지 노출시 demo
  .instructions {
    /* position: relative; */
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
    color: #ff2c2c;
    font-weight: 400;
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
`

// 🤖  Regex set ----------------------------------------
const REGISTER_URL = 'http://52.78.152.135:8080/members'

const EMAIL_REGEX = new RegExp('[a-z0-9]+@[a-z]+.[a-z]{2,3}')
const USERNAME_REGEX = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/
// 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성
// * 한글 초성 및 모음은 허용하지 않음
const PWD_REGEX = /(?=.*[0-9])(?=.*[a-z]).{8,20}/
// 8자 이상 20자 이하, 숫자와 영문의 조합으로 구성

// 🤖 valid set ----------------------------------------
const Register = () => {
  const userRef = useRef()
  const errRef = useRef()

  const [email, setEmail] = useState('')
  const [validEmail, setValidEmail] = useState(false)
  const [emailFocus, setEmailFocus] = useState(false)
  const [emailMessage, setEmailMessage] = useState('')

  const [nickName, setNickname] = useState('')
  const [validnickName, setValidnickName] = useState(false)
  const [userFocus, setUserFocus] = useState(false)
  const [nickNameMessage, setNickNameMessage] = useState('')

  const [password, setPassword] = useState('')
  const [validPwd, setValidPwd] = useState(false)
  const [pwdFocus, setPwdFocus] = useState(false)

  const [matchPwd, setMatchPwd] = useState('')
  const [validMatch, setValidMatch] = useState(false)
  const [matchFocus, setMatchFocus] = useState(false)

  const [errMsg, setErrMsg] = useState('')
  const [success, setSuccess] = useState(false)

  useEffect(() => {
    userRef.current.focus()
  }, [])

  useEffect(() => {
    const vaildEmail = EMAIL_REGEX.test(email)
    if (vaildEmail) {
      // printValue(email, 'email')
    }
    setValidEmail(vaildEmail)
  }, [email])

  useEffect(() => {
    const vaildNickName = USERNAME_REGEX.test(nickName)
    if (vaildNickName) {
      // printValue(nickName, 'nickname')
    }
    setValidnickName(USERNAME_REGEX.test(nickName))
  }, [nickName])

  useEffect(() => {
    setValidPwd(PWD_REGEX.test(password))
    setValidMatch(password === matchPwd)
  }, [password, matchPwd])

  useEffect(() => {
    setErrMsg('')
  }, [email, nickName, password, matchPwd])

  // 🤖 회원가입 버튼 핸들링 ----------------------------------------
  const handleSubmit = async e => {
    e.preventDefault()
    // 버튼 활성화 되었을 경우 (js hack)
    const v1 = EMAIL_REGEX.test(email)
    const v2 = USERNAME_REGEX.test(nickName)
    const v3 = PWD_REGEX.test(password)
    if (!v1 || !v2 || !v3) {
      setErrMsg('입력된 정보들을 다시 확인해주세요.')
      return
    }
    try {
      const response = await axios.post(
        REGISTER_URL,
        { email, nickName, password },
        {
          headers: { 'Content-Type': 'application/json', withCredentials: true },
        },
      )
      console.log(response?.data)
      console.log(response?.accessToken)
      console.log(JSON.stringify(response))
      setSuccess(true)
      // 상태 및 인풋 날리기 clean!
      // 입력을 위한 대한 속성 값 필요
      setEmail('')
      setNickname('')
      setPassword('')
      setMatchPwd('')
    } catch (err) {
      if (!err?.response) {
        setErrMsg('서버의 응답이 없습니다. 새로고침 후 다시 시도해주세요.😭')
      } else if (err.response?.status === 409) {
        setErrMsg('이미 사용중인 닉네임과 비밀번호입니다. 변경해주세요.')
      } else {
        setErrMsg('회원 가입에 실패했습니다.😭 다시 시도해주세요.')
      }
      errRef.current.focus()
    }
  }
  return (
    <Container>
      {success ? (
        <section className="cmm-form">
          <h1>회원가입을 환영합니다 🥰</h1>
          <p>
            <Link to="/signin">로그인 화면으로 이동하기</Link>
          </p>
        </section>
      ) : (
        <section className="cmm-form">
          <form onSubmit={handleSubmit}>
            <legend>회원가입</legend>
            <p className="sign-about">
              회원가입을 하신 적이 있으신가요?
              <span className="line">
                <Link to="/signin">로그인 하기</Link>
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
                // 멘토님 Q. 지금 비효율 적임 만약 새로 짠다면?
                // 기존로직: onChange > setEmail > email changed > email 종속 useEffect > 유효성 체크 > 유효성 결과를 > 유효성 메세지 상태에 저장
                // 필요없는 부분 줄여보기: onChange > 유효성을 체크 함수 > 유효성을 체크하고 메세지 저장 & setEmail
                // email = {value: "", isValided: false, validatedMessage:""}
                value={email}
                required
                aria-invalid={validEmail ? 'false' : 'true'}
                aria-describedby="uidnote"
                onFocus={() => setEmailFocus(true)}
                onBlur={() => setEmailFocus(false)}
              />
              <label htmlFor="email">
                {/* 멘토님 Q. 유효성 CSS 프롭이나 연산자 이용해서 변경하기를 만약 변경한다면.. 
                {validEmail && <FontAwesomeIcon icon={faCircleCheck} />}
                {!validEmail && <FontAwesomeIcon icon={faCircleXmark} />} */}
                <FontAwesomeIcon icon={faCircleCheck} className={validEmail ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validEmail || !nickName ? `hide` : 'invalid'} />
              </label>
            </div>
            <p id="uidnote" className={emailFocus && email && !validEmail ? 'instructions' : 'offscreen'}>
              이메일 형태로 입력해주세요.
            </p>
            <p>{emailMessage}</p>

            <div className="input-wrap">
              <input
                placeholder="닉네임을 입력해주세요."
                type="text"
                id="nickName"
                ref={userRef}
                autoComplete="off"
                onChange={e => setNickname(e.target.value)}
                value={nickName}
                required
                aria-invalid={validnickName ? 'false' : 'true'}
                aria-describedby="uidnote"
                onFocus={() => setUserFocus(true)}
                onBlur={() => setUserFocus(false)}
              />
              <label htmlFor="nickName">
                <FontAwesomeIcon icon={faCircleCheck} className={validnickName ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validnickName || !nickName ? 'hide' : 'invalid'} />
              </label>
            </div>
            <p id="uidnote" className={userFocus && nickName && !validnickName ? 'instructions' : 'offscreen'}>
              4글자에서 24글자 이내로 입력해주세요. <br />
              문자, 숫자, 밑줄, 하이픈은 사용가능해요.
            </p>
            <p>{nickNameMessage}</p>
            <div className="input-wrap">
              <input
                placeholder="비밀번호를 입력해주세요."
                type="password"
                id="password"
                onChange={e => setPassword(e.target.value)}
                value={password}
                required
                aria-invalid={validPwd ? 'false' : 'true'}
                aria-describedby="pwdnote"
                onFocus={() => setPwdFocus(true)}
                onBlur={() => setPwdFocus(false)}
              />
              <label htmlFor="password">
                <FontAwesomeIcon icon={faCircleCheck} className={validPwd ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validPwd || !password ? 'hide' : 'invalid'} />
              </label>
            </div>
            <p id="pwdnote" className={pwdFocus && !validPwd ? 'instructions' : 'offscreen'}>
              8글자에서 24글자 이내로 입력해주세요.
            </p>
            <div className="input-wrap">
              <input
                placeholder="비밀번호를 재입력해주세요."
                type="password"
                id="confirm_pwd"
                onChange={e => setMatchPwd(e.target.value)}
                value={matchPwd}
                required
                aria-invalid={validMatch ? 'false' : 'true'}
                aria-describedby="confirmnote"
                onFocus={() => setMatchFocus(true)}
                onBlur={() => setMatchFocus(false)}
              />
              <label htmlFor="confirm_pwd">
                <FontAwesomeIcon icon={faCircleCheck} className={validMatch && matchPwd ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validMatch || !matchPwd ? 'hide' : 'invalid'} />
              </label>
            </div>
            <p id="confirmnote" className={matchFocus && !validMatch ? 'instructions' : 'offscreen'}>
              첫 번째 비밀번호와 일치하게 수정해주세요.🥹
            </p>
            <button className={!validnickName || !validPwd || !validMatch ? 'btn-disabled' : 'btn-submit'}>
              회원가입
            </button>
            <div ref={errRef} className={errMsg ? 'errmsg' : 'offscreen'} aria-live="assertive">
              {errMsg}
            </div>
          </form>
        </section>
      )}
    </Container>
  )
}

export default Register

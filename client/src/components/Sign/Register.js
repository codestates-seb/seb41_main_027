import axios from '../../api/sign'
import { useRef, useState, useEffect } from 'react'
import { Link } from 'react-router-dom'

// import '../Sign/Register.css'
import styled from 'styled-components'
import { faCircleCheck, faCircleXmark, faInfoCircle } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

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

//💄 Demo Regex ---------------------------------
const REGISTER_URL = 'http://localhost:3001/signup'

const EMAIL_REGEX = new RegExp('[a-z0-9]+@[a-z]+.[a-z]{2,3}')
const USER_REGEX = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/
// 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성
// *한글 초성 및 모음은 허가하지 않음
const PWD_REGEX = /(?=.*[0-9])(?=.*[a-z]).{8,20}/
// 8자 이상 20자 이하, 숫자와 영문의 조합으로 구성

// 🚧 더미 추후 날리겠습니다. ----------------------------------------
// const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/
// const EMAIL_REGEX = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/
// const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/

const Register = () => {
  const userRef = useRef()
  const errRef = useRef()

  const [email, setEmail] = useState('')
  const [validEmail, setValidEmail] = useState(false)
  const [emailFocus, setEmailFocus] = useState(false)

  const [username, setUsername] = useState('')
  const [validUsername, setValidUsername] = useState(false)
  const [userFocus, setUserFocus] = useState(false)

  const [pwd, setPwd] = useState('')
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
    setValidEmail(EMAIL_REGEX.test(email))
  }, [email])

  useEffect(() => {
    setValidUsername(USER_REGEX.test(username))
  }, [username])

  useEffect(() => {
    setValidPwd(PWD_REGEX.test(pwd))
    setValidMatch(pwd === matchPwd)
  }, [pwd, matchPwd])

  useEffect(() => {
    setErrMsg('')
  }, [email, username, pwd, matchPwd])

  const handleSubmit = async e => {
    e.preventDefault()
    // 버튼 활성화 되었을 경우 (js hack)
    const v1 = EMAIL_REGEX.test(email)
    const v2 = USER_REGEX.test(username)
    const v3 = PWD_REGEX.test(pwd)
    if (!v1 || !v2 || !v3) {
      setErrMsg('입력된 정보를 다시 확인해주세요.')
      return
    }
    try {
      const response = await axios.post(REGISTER_URL, JSON.stringify({ email, username, pwd }), {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: true,
      })
      console.log(response?.data)
      console.log(response?.accessToken)
      console.log(JSON.stringify(response))
      setSuccess(true)
      // 상태 및 인풋 날리기 clean!
      // 입력을 위한 대한 속성 값 필요
      setEmail('')
      setUsername('')
      setPwd('')
      setMatchPwd('')
    } catch (err) {
      if (!err?.response) {
        setErrMsg('서버의 응답이 없습니다. 새로고침을 해주세요.')
      } else if (err.response?.status === 404) {
        setErrMsg('이미 사용중인 닉네임입니다. 닉네임을 변경해주세요.')
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
                value={email}
                required
                aria-invalid={validEmail ? 'false' : 'true'}
                aria-describedby="uidnote"
                onFocus={() => setEmailFocus(true)}
                onBlur={() => setEmailFocus(false)}
              />
              <label htmlFor="email">
                <FontAwesomeIcon icon={faCircleCheck} className={validEmail ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validEmail || !username ? 'hide' : 'invalid'} />
              </label>
            </div>
            <p id="uidnote" className={emailFocus && email && !validEmail ? 'instructions' : 'offscreen'}>
              이메일 형태로 입력해주세요.
            </p>
            <div className="input-wrap">
              <input
                placeholder="닉네임을 입력해주세요."
                type="text"
                id="username"
                ref={userRef}
                autoComplete="off"
                onChange={e => setUsername(e.target.value)}
                value={username}
                required
                aria-invalid={validUsername ? 'false' : 'true'}
                aria-describedby="uidnote"
                onFocus={() => setUserFocus(true)}
                onBlur={() => setUserFocus(false)}
              />
              <label htmlFor="username">
                <FontAwesomeIcon icon={faCircleCheck} className={validUsername ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validUsername || !username ? 'hide' : 'invalid'} />
              </label>
            </div>
            <p id="uidnote" className={userFocus && username && !validUsername ? 'instructions' : 'offscreen'}>
              4글자에서 24글자 이내로 입력해주세요. <br />
              문자, 숫자, 밑줄, 하이픈은 사용가능해요.
            </p>
            <div className="input-wrap">
              <input
                placeholder="비밀번호를 입력해주세요."
                type="password"
                id="password"
                onChange={e => setPwd(e.target.value)}
                value={pwd}
                required
                aria-invalid={validPwd ? 'false' : 'true'}
                aria-describedby="pwdnote"
                onFocus={() => setPwdFocus(true)}
                onBlur={() => setPwdFocus(false)}
              />
              <label htmlFor="password">
                <FontAwesomeIcon icon={faCircleCheck} className={validPwd ? 'valid' : 'hide'} />
                <FontAwesomeIcon icon={faCircleXmark} className={validPwd || !pwd ? 'hide' : 'invalid'} />
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
            <button className={!validUsername || !validPwd || !validMatch ? 'btn-disabled' : 'btn-submit'}>
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

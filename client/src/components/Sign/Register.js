import { useRef, useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import styled from 'styled-components'

import { customAxios } from '../../utils/customAxios'
import { API_MEMBER_ENDPOINT, MEMBER_PWD_REGEX } from '../../utils/const'

import { faCircleCheck, faCircleXmark } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

//π Demo Styles --------------------------------
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
    /* gap: 4px; btn λ° ico μΆκ°μ */
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

  // μ ν¨μ± μλ¬λ©μΈμ§ λΉλΈμΆμ demo
  .offscreen {
    display: none;
  }
  // μ ν¨μ± μλ¬λ©μΈμ§ λΈμΆμ demo
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
`

// π€  Regex set ----------------------------------------
const EMAIL_REGEX = new RegExp('[a-z0-9]+@[a-z]+.[a-z]{2,3}')
const USERNAME_REGEX = /^(?=.*[a-z0-9κ°-ν£])[a-z0-9κ°-ν£]{2,16}$/
// 2μ μ΄μ 16μ μ΄ν, μμ΄ λλ μ«μ λλ νκΈλ‘ κ΅¬μ±
// * νκΈ μ΄μ± λ° λͺ¨μμ νμ©νμ§ μμ
// 8μ μ΄μ 20μ μ΄ν, μ«μμ μλ¬Έμ μ‘°ν©μΌλ‘ κ΅¬μ±

// π€ valid set ----------------------------------------
const Register = () => {
  const userRef = useRef()
  const errRef = useRef()
  const navigate = useNavigate()

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
    setValidPwd(MEMBER_PWD_REGEX.test(password))
    setValidMatch(password === matchPwd)
  }, [password, matchPwd])

  useEffect(() => {
    setErrMsg('')
  }, [email, nickName, password, matchPwd])

  // π€ νμκ°μ λ²νΌ νΈλ€λ§ ----------------------------------------
  const handleSubmit = async e => {
    e.preventDefault()
    // λ²νΌ νμ±ν λμμ κ²½μ° (js hack)
    const v1 = EMAIL_REGEX.test(email)
    const v2 = USERNAME_REGEX.test(nickName)
    const v3 = MEMBER_PWD_REGEX.test(password)
    if (!v1 || !v2 || !v3) {
      setErrMsg('μλ ₯λ μ λ³΄λ€μ λ€μ νμΈν΄μ£ΌμΈμ.')
      return
    }
    try {
      const response = await customAxios.post(API_MEMBER_ENDPOINT, { email, nickName, password })
      // μν λ° μΈν λ λ¦¬κΈ° clean!
      // μλ ₯μ μν λν μμ± κ° νμ
      setEmail('')
      setNickname('')
      setPassword('')
      setMatchPwd('')
      toast.success('νμκ°μμ΄ μλ£λμμ΅λλ€.')
      navigate('/signin')
    } catch (err) {
      console.log('err', err)
      if (!err?.response) {
        setErrMsg(
          <p>
            μλ²μ μλ΅μ΄ μμ΄μ. <br />
            μλ‘κ³ μΉ¨ ν λ€μ μλν΄μ£ΌμΈμ.π­
          </p>,
        )
      } else if (err.response?.data.message === 'This Email Already Exists') {
        setErrMsg(
          <p>
            μ΄λ―Έ μ¬μ©μ€μΈ μ΄λ©μΌμ΄μμ. <br />
            λ³κ²½ν΄μ£ΌμΈμ.π₯Ή
          </p>,
        )
      } else if (err.response?.data.message === 'This NickName Already Exists') {
        setErrMsg(
          <p>
            μ΄λ―Έ μ¬μ©μ€μΈλλ€μ μ΄μμ. <br />
            λ³κ²½ν΄μ£ΌμΈμ.π₯Ή
          </p>,
        )
      } else if (err.response?.data.message === 'Email and NickName Already Exists') {
        setErrMsg(
          <p>
            μ΄λ―Έ μ¬μ©μ€μΈ μ΄λ©μΌκ³Ό λλ€μ μ΄μμ. <br />
            λ³κ²½ν΄μ£ΌμΈμ.π₯Ή
          </p>,
        )
      } else {
        setErrMsg(
          <p>
            νμκ°μμ μ€ν¨νμ΄μ.
            <br />
            μλ‘κ³ μΉ¨ ν λ€μ μλν΄μ£ΌμΈμ.π­
          </p>,
        )
      }
      errRef.current.focus()
    }
  }
  return (
    <Container>
      <section className="cmm-form">
        <form onSubmit={handleSubmit}>
          <legend>νμκ°μ</legend>
          <p className="sign-about">
            νμκ°μμ νμ  μ μ΄ μμΌμ κ°μ?
            <span className="line">
              <Link to="/signin">λ‘κ·ΈμΈ νκΈ°</Link>
            </span>
          </p>
          <div className="input-wrap">
            <input
              placeholder="μ΄λ©μΌμ μλ ₯ν΄μ£ΌμΈμ."
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
              <FontAwesomeIcon icon={faCircleXmark} className={validEmail || !nickName ? `hide` : 'invalid'} />
            </label>
          </div>
          <p id="uidnote" className={emailFocus && email && !validEmail ? 'instructions' : 'offscreen'}>
            μ΄λ©μΌ ννλ‘ μλ ₯ν΄μ£ΌμΈμ.
          </p>
          <p>{emailMessage}</p>

          <div className="input-wrap">
            <input
              placeholder="λλ€μμ μλ ₯ν΄μ£ΌμΈμ."
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
            4κΈμμμ 24κΈμ μ΄λ΄λ‘ μλ ₯ν΄μ£ΌμΈμ. <br />
            λ¬Έμ, μ«μ, λ°μ€, νμ΄νμ μ¬μ©κ°λ₯ν΄μ.
          </p>
          <p>{nickNameMessage}</p>
          <div className="input-wrap">
            <input
              placeholder="λΉλ°λ²νΈλ₯Ό μλ ₯ν΄μ£ΌμΈμ."
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
            8κΈμμμ 24κΈμ μ΄λ΄λ‘ μλ ₯ν΄μ£ΌμΈμ.
          </p>
          <div className="input-wrap">
            <input
              placeholder="λΉλ°λ²νΈλ₯Ό μ¬μλ ₯ν΄μ£ΌμΈμ."
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
            μ²« λ²μ§Έ λΉλ°λ²νΈμ μΌμΉνκ² μμ ν΄μ£ΌμΈμ.π₯Ή
          </p>
          <button className={!validnickName || !validPwd || !validMatch ? 'btn-disabled' : 'btn-submit'}>
            νμκ°μ
          </button>
          <div ref={errRef} className={errMsg ? 'errmsg' : 'offscreen'} aria-live="assertive">
            {errMsg}
          </div>
        </form>
      </section>
    </Container>
  )
}

export default Register

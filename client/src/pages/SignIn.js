import axios from 'axios'
import { useState, useEffect, useRef } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
// import { useForm } from 'react-hook-form';

import { API_LOGIN_ENDPOINT, MEMBER_PWD_REGEX } from '../utils/const'
import { customAxios } from '../utils/customAxios'
import { setLoginInfo } from '../api/login'

import styled from 'styled-components'
import Logo from '../assets/LogoTypeSignature.svg'

//π Demo Styles --------------------------------
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
    position: absolute;
    left: -9999px;
  }
  // μ ν¨μ± μλ¬λ©μΈμ§ λΈμΆμ demo
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

// π€  Regex set ----------------------------------------
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
    // λ²νΌ νμ±ν λμμ κ²½μ° (js hack)
    const v1 = EMAIL_REGEX.test(email)
    const v2 = MEMBER_PWD_REGEX.test(password)
    if (!v1 || !v2) {
      setErrMsg('μλ ₯λ μ λ³΄λ₯Ό λ€μ νμΈν΄μ£ΌμΈμ.')
      return
    }
    try {
      const response = await customAxios.post(API_LOGIN_ENDPOINT, { username: email, password }).then(response => {
        // localstorage login info save
        const { memberId, nickName } = response.data
        const { authorization, refresh } = response.headers
        setLoginInfo(memberId, nickName, authorization, refresh)
        navigate(callbackUrl, { replace: true })
        // μν λ° μΈν λ λ¦¬κΈ° clean!
        // μλ ₯μ μν λν μμ± κ° νμ
        setEmail('')
        setpassword('')
      })
    } catch (err) {
      // 1. DBμ κ³μ μ΄ μ‘΄μ¬νμ§ μμ: 404
      // 2. μ΄λ―Έ λ±λ‘λμ΄ μλ κ³μ μΈλ° + λΉλ°λ²νΈκ° νλ Έλ€λ©΄: 400
      console.log('err', err)
      if (!err?.response) {
        setErrMsg(
          <p>
            μλ²μ μλ΅μ΄ μμ΄μ. <br />
            μλ‘κ³ μΉ¨ ν λ€μ μλν΄μ£ΌμΈμ.π­
          </p>,
        )
      } else if (err.response?.status === 400) {
        setErrMsg(
          <p>
            λΉλ°λ²νΈκ° λ§μ§ μμμ.
            <br />
            λ€μ ν λ² νμΈ ν΄μ£ΌμΈμ. π₯Ή
          </p>,
        )
      } else if (err.response?.status === 404) {
        setErrMsg(
          <p>
            μ‘΄μ¬νμ§ μλ κ³μ μ΄μμ.
            <br /> μ΄λ©μΌμ λ€μ ν λ² νμΈ ν΄μ£ΌμΈμ. π₯Ή
          </p>,
        )
      } else {
        setErrMsg(
          <p>
            λ‘κ·ΈμΈμ μ€ν¨νμ΄μ.
            <br />
            μλ‘κ³ μΉ¨ ν λ€μ μλν΄μ£ΌμΈμ.π­
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
            <legend>λ‘κ·ΈμΈ</legend>
            <p className="sign-about">
              νμκ°μμ νμ  μ μ΄ μμΌμ κ°μ?
              <span className="line">
                <Link to="/signup">νμκ°μ νκΈ°</Link>
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
            </div>
            <p id="uidnote" className={emailFocus && email && !validEmail ? 'instructions' : 'offscreen'}>
              μ΄λ©μΌ ννλ‘ μλ ₯ν΄μ£ΌμΈμ.
            </p>
            <div className="input-wrap">
              <input
                placeholder="λΉλ°λ²νΈλ₯Ό μλ ₯ν΄μ£ΌμΈμ."
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
              8κΈμμμ 24κΈμ μ΄λ΄λ‘ μλ ₯ν΄μ£ΌμΈμ.
            </p>
            <button className={!validEmail || !validPassword ? 'btn-disabled' : 'btn-submit'}>λ‘κ·ΈμΈ</button>
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

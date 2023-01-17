import React from 'react'
import axios from 'axios'
// import { useForm } from 'react-hook-form';
// import { useNavigate } from 'react-router-dom'; 뭔차이? 모름 ㅎㅎ안쓸듯
// import React, { useState, useEffect } from 'react' 회원가입시
import styled from 'styled-components'
import { Link } from 'react-router-dom'

import Logo from '../assets/LogoTypeSignature.svg'

//💄 Demo Styles ---------------------------------
const Container = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .cmm-form {
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
    legend {
      color: #363738;
      font-size: 24px;
      font-weight: 600;
    }
    p {
      margin: 8px 0 16px 0;
      gap: 8px;
      font-weight: 300;
      font-size: 12px;
      a {
        color: #000;
        padding-left: 8px;
        font-weight: 600;
      }
    }
  }

  .SearchQueryInput {
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
    ::placeholder {
      color: rgb(0 0 0 / 40%);
      font-weight: 400;
      font-size: 14px;
      line-height: 14px;
    }
  }
  .btn-submit {
    cursor: pointer;
    width: 100%;
    height: 48px;
    margin-top: 32px;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 4px;
    color: #fff;
    font-size: 20px;
    font-weight: 500;
    letter-spacing: -0.1px;
    background: #384452;
    border-radius: 12px;
    :hover {
      background: #222a33;
    }
  }
`

const SignIn = () => {
  return (
    <Container>
      <h1>
        <img src={Logo} className="Logo" alt="EcoGreenSeoul Logo TypeSignature" />
      </h1>
      <form className="cmm-form">
        <legend>로그인</legend>
        <p>
          계정이 없으신가요?
          <Link to="/signup">회원가입하기</Link>
        </p>
        <input
          className="SearchQueryInput"
          type="text"
          name=""
          placeholder="이메일을 입력해주세요."
          defaultValue={''}
        />
        <input
          className="SearchQueryInput"
          type="text"
          name=""
          placeholder="비밀번호를  입력해주세요."
          defaultValue={''}
        />
        <button className="btn-submit" type="submit" name="">
          로그인
        </button>
      </form>
    </Container>
  )
}

export default SignIn

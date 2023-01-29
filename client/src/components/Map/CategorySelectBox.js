import { useState } from 'react'
import { useRecoilState, useRecoilValue, useResetRecoilState } from 'recoil'
import styled from 'styled-components'
import { categoryValue } from '../../recoil/atoms'

const Wrapper = styled.div`
  position: relative;
  width: 306px;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
  // Dropdown ico Demo
  &::after {
    position: absolute;
    top: 4px;
    right: 8px;
    margin-right: 8px;
    content: '▾';
    // color: #393D45;
    color: #737980;
    font-size: 32px;
    pointer-events: none;
  }

  // 🚧 Default state
  .select {
    cursor: pointer;
    padding: 12px 16px;
    width: 100%;
    height: 48px;
    font: inherit;
    font-size: 18px;
    -moz-appearance: none;
    -webkit-appearance: none;
    background: #fff;
    border: 1px solid #d2d5e1;
    // box-shadow: 0px 4px 16px rgba(8, 9, 10, 0.2);
    box-shadow: 0 0 1em 0 rgba(0, 0, 0, 0.2);
    border-radius: 16px;
    &:focus {
      color: black;
    }
    // option IE 11+
    &::-ms-expand {
      display: none;
    }
    &:invalid {
      color: blue;
    }
  }
  .select option[value=''][disabled] {
    display: none;
  }
`

const CategorySelectBox = () => {
  // State
  const [selectedValue, setSelectedValue] = useRecoilState(categoryValue)

  // Handle
  const handleChange = e => {
    setSelectedValue(e.target.value)
  }
  return (
    <Wrapper className="select-wrapper">
      <select
        id="select"
        className="select"
        onChange={e => {
          handleChange(e)
        }}
        required
      >
        <option value="" disabled selected>
          카테고리를 설정해 주세요
        </option>
        <option value="1">제로웨이스트샵</option>
        <option value="2">공방</option>
        <option value="3">푸드</option>
        <option value="4">카페</option>
      </select>
    </Wrapper>
  )
}

export default CategorySelectBox

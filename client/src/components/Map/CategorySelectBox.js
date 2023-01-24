import styled from 'styled-components'

const Wrapper = styled.div`
  position: relative;
  width: 408px;
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
  .option[value=''][disabled] {
    display: none;
  }
`

const CategorySelectBox = () => {
  return (
    <Wrapper className="select-wrapper">
      <select className="select" required>
        <option value="" disabled selected>
          카테고리를 설정해 주세요
        </option>
        <option value="제로웨이스트샵">제로웨이스트샵</option>
        <option value="공방">공방</option>
        <option value="푸드">푸드</option>
        <option value="카페">카페</option>
      </select>
    </Wrapper>
  )
}

export default CategorySelectBox

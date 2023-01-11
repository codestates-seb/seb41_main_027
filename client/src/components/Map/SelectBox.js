import styled from 'styled-components'

const Wrapper = styled.div`
  position: relative;
  width: 180px;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
  // Dropdown ico demo
  &::after {
    // color: #393D45;
    color: #737980;
    content: '▾';
    margin-right: 8px;
    pointer-events: none;
    position: absolute;
    right: 8px;
    top: 6px;
    font-size: 24px;
  }

  // Default state
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
    // Hack for IE 11+
    &::-ms-expand {
      display: none;
    }
  }
`

const SearchBar = () => {
  return (
    <Wrapper className="select-wrapper">
      <select className="select">
        <option value="category0">전체보기</option>
        <option value="category2">제로웨이스트샵</option>
        <option value="category3">공방</option>
        <option value="category4">푸드</option>
        <option value="category5">카페</option>
      </select>
    </Wrapper>
  )
}

export default SearchBar

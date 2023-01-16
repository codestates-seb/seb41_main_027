import styled from 'styled-components'

const Wrapper = styled.div`
  position: relative;
  width: 180px;
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
  }
`

const SearchBar = () => {
  return (
    <Wrapper className="select-wrapper">
      <select className="select" defaultValue={'전체보기'}>
        <option>전체보기</option>
        <option>제로웨이스트샵</option>
        <option>공방</option>
        <option>푸드</option>
        <option>카페</option>
      </select>
    </Wrapper>
  )
}

export default SearchBar

import styled from 'styled-components'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import { useState } from 'react'
import { useRecoilState } from 'recoil'
import { searchValue } from '../../../recoil/atoms'

// li사이즈만 빼둠 Nav css 셋업 안된 상태 -> 추후 Acitve 스타일링 필요
// <a href="/" className={type.page === "home" ? "selected" : ""}>

const StyleFontAwesomeIcon = styled(FontAwesomeIcon)`
  font-size: 24px;
  color: #13c57c;
`

const Wrapper = styled.div`
  width: 400px;
  max-width: 100%;

  .SearchBar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-sizing: border-box;
    padding: 4px 16px;
    height: 48px;
    border: 1px solid #d2d5e1;
    border-radius: 16px;
    background-color: #fff;
    box-shadow: 0 0 1em 0 rgba(0, 0, 0, 0.2);
  }
  .SearchQueryInput {
    color: #384452;
    ::placeholder {
      color: rgb(0 0 0 / 40%);
      font-weight: 400;
      font-size: 18px;
    }
  }
  .SearchQuerySubmit {
  }
`

const SearchInput = () => {
  const [keyword, setKeyword] = useRecoilState(searchValue)
  const onChangeSearch = e => {
    e.preventDefault()
    setKeyword(e.target.value)
    console.log(keyword)
  }
  return (
    <Wrapper>
      <form>
        <div className="SearchBar">
          <input
            value={keyword}
            className="SearchQueryInput"
            type="text"
            name=""
            placeholder="장소를 검색해주세요."
            onChange={onChangeSearch}
          />
          <button className="SearchQuerySubmit" type="submit" name="">
            <StyleFontAwesomeIcon icon={faSearch} />
          </button>
        </div>
      </form>
    </Wrapper>
  )
}

export default SearchInput

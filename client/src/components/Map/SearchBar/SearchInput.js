import styled from 'styled-components'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch, faRotateLeft } from '@fortawesome/free-solid-svg-icons'
import { useState } from 'react'
import { useRecoilState } from 'recoil'
import { searchValue } from '../../../recoil/atoms'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

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
  .btn-wrap {
    display: flex;
    flex-direction: row;
    gap: 8px;
    .btn-reset {
      width: 40px;
      height: 40px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
      font-size: 16px;
      background: #17ac52;
      border-radius: 12px;
    }
  }
`

const SearchInput = ({ displayNone }) => {
  // state, hook
  const [keyword, setKeyword] = useRecoilState(searchValue)
  const [search, setSearch] = useState()
  const navigate = useNavigate()

  // handler
  const handleOnKeyPress = e => {
    if (e.key === 'Enter') {
      // setSearch(e.target.value)
      e.preventDefault()
      handleSearch(e)
    }
  }
  const onChangeSearch = e => {
    e.preventDefault()
    setSearch(e.target.value)
  }
  const handleSearch = e => {
    e.preventDefault()
    if (search && search.length > 1) {
      setKeyword(search)
    } else {
      // e.preventDefault()
      toast.error('최소 2글자 이상이어야 합니다.')
    }
  }
  const onClickReset = () => {
    setKeyword('')
    // navigate(`/`)
  }
  return (
    <Wrapper>
      <form>
        <div className="SearchBar">
          <input
            value={search || ''}
            className="SearchQueryInput"
            type="text"
            name=""
            placeholder="장소를 검색해주세요."
            onChange={onChangeSearch}
            onKeyPress={handleOnKeyPress}
          />
          <div className="btn-wrap">
            <button className="SearchQuerySubmit" name="" disabled={!search}>
              <StyleFontAwesomeIcon icon={faSearch} onClick={handleSearch} />
            </button>
            {!displayNone && keyword && (
              <button className="btn-reset" onClick={onClickReset}>
                <FontAwesomeIcon icon={faRotateLeft} />
              </button>
            )}
          </div>
        </div>
      </form>
    </Wrapper>
  )
}

export default SearchInput

import styled from 'styled-components'

import SelectBox from './SelectBox'
import RadioButton from './RadioButton'
import SearchInput from './SearchInput'
import { useRecoilState, useRecoilValue } from 'recoil'
import { searchValue } from '../../../recoil/atoms'
import { Link, useNavigate } from 'react-router-dom'

const Wrapper = styled.div`
  // Position 🫡
  position: absolute;
  z-index: 1500;
  left: 50%;
  transform: translate(-50%, 0%);
  display: flex;
  margin-top: 40px;
  gap: 16px;
  justify-content: center;
`

const SearchBar = () => {
  // state
  const [resetKeyword, setResetKeyword] = useRecoilState(searchValue)
  const navigate = useNavigate()
  const onClickReset = () => {
    setResetKeyword('')
    navigate(`/`)
  }
  return (
    <Wrapper>
      <RadioButton />
      <SelectBox />
      <SearchInput />
      {resetKeyword && <button onClick={onClickReset}>초기화</button>}
    </Wrapper>
  )
}

export default SearchBar

import styled from 'styled-components'

import SelectBox from './SelectBox'
import RadioButton from './RadioButton'
import SearchInput from './SearchInput'
import { useRecoilValue } from 'recoil'
import { searchValue } from '../../../recoil/atoms'
import { Link } from 'react-router-dom'

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
  const resetKeyword = useRecoilValue(searchValue)
  return (
    <Wrapper>
      <RadioButton />
      <SelectBox />
      <SearchInput />
      {resetKeyword && <Link to="/">초기화</Link>}
    </Wrapper>
  )
}

export default SearchBar

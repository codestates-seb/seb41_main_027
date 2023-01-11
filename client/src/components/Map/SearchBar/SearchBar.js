import styled from 'styled-components'

import SelectBox from './SelectBox'
import RadioButton from './RadioButton'
import SearchInput from './SearchInput'

const Wrapper = styled.div`
  // Position 🫡
  display: flex;
  margin-top: 16px;
  gap: 16px;
  justify-content: center;
`

const SearchBar = () => {
  return (
    <Wrapper>
      <RadioButton />
      <SelectBox />
      <SearchInput />
    </Wrapper>
  )
}

export default SearchBar

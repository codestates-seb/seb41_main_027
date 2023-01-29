import styled from 'styled-components'
import SelectBox from './SelectBox'
import RadioButton from './RadioButton'
import SearchInput from './SearchInput'

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
  return (
    <Wrapper>
      <RadioButton />
      <SelectBox />
      <SearchInput />
    </Wrapper>
  )
}

export default SearchBar

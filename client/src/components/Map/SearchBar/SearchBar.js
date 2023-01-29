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

// .resetBtn {
//   position: relative;
//   display: flex;
//   flex-direction: column;
//   /* margin: 5px 210px; */
//   border: 1px solid #d2d5e1;
//   padding: 8px;
//   height: 38px;
//   width: 60px;
//   border-radius: 16px;
//   background-color: #fff;
//   color: rgb(5, 129, 187);
// }
const SearchBar = () => {
  // state
  // const [resetKeyword, setResetKeyword] = useRecoilState(searchValue)
  // const navigate = useNavigate()

  // // handler
  // const onClickReset = () => {
  //   setResetKeyword('')
  //   navigate(`/`)
  // }
  return (
    <Wrapper>
      <RadioButton />
      <SelectBox />
      <SearchInput />
      {/* {resetKeyword && (
        <button className="resetBtn" onClick={onClickReset}>
          초기화
        </button>
      )} */}
    </Wrapper>
  )
}

export default SearchBar

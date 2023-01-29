import CategorySelectBox from './CategorySelectBox'
import { useLocation, useNavigate } from 'react-router-dom'
import { useState } from 'react'
import { useRecoilState, useRecoilValue } from 'recoil'
import { categoryValue, searchValue } from '../../recoil/atoms'
import * as place from '../../api/place'
import styled from 'styled-components'

const StyledPlace = styled.div`
  margin: 20px 0 20px 0;
  position: relative;
  display: flex;
  flex-direction: column;
  .place-title,
  .place-address {
    margin: 10px 0 10px 0;
    background-color: white;
    padding: 10px;
    border-radius: 18px;
  }
  .description_text {
    margin-top: 20px;
  }
  .placeInfo-save-btn {
    margin: 30px 0 10px 0;
    padding: 10px 20px;
    height: 40px;
    border-radius: 18px;
    background-color: #da4c1f;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`

const AddPlaceInfo = () => {
  const location = useLocation()

  // state, hook
  const [selectedCategory, setSelectedCategory] = useState(null)
  const category = useRecoilValue(categoryValue)
  const [keywordReset, setKeywordReset] = useRecoilState(searchValue)
  const navigate = useNavigate()

  // handle
  const handleSubmitPlaceInfo = e => {
    e.preventDefault()
    const description = e.target.description_text.value

    // db create
    const body = {
      name: `${location.state.position.name}`,
      address: `${location.state.position.address}`,
      description: description,
      kakaoId: `${location.state.position.id}`,
      categoryId: category,
      latitude: `${location.state.position.position.lat}`,
      longitude: `${location.state.position.position.lng}`,
    }
    place.createPlace(body).then(data => {
      console.log('created place', data)
    })
    setKeywordReset('')
    navigate(`/`)
  }

  return (
    <form className="review-add-form" onSubmit={handleSubmitPlaceInfo}>
      <CategorySelectBox />
      <StyledPlace>
        <div>장소 이름</div>
        <div className="place-title">{location.state.position.name}</div>
        <div>주소</div>
        <div className="place-address">{location.state.position.address}</div>
        <textarea
          className="description_text"
          name="description_text"
          maxLength={200}
          placeholder="장소에 대한 Comment를 입력해 주세요."
          required
        />
        <button type="submit" className="placeInfo-save-btn" disabled={false}>
          등록하기
        </button>
      </StyledPlace>
    </form>
  )
}
export default AddPlaceInfo

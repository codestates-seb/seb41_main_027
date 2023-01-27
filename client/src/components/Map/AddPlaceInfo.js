import CategorySelectBox from './CategorySelectBox'
import { useLocation } from 'react-router-dom'
import { useState } from 'react'
import { useRecoilValue } from 'recoil'
import { categoryValue } from '../../recoil/atoms'
import * as place from '../../api/place'

const AddPlaceInfo = () => {
  const location = useLocation()
  // console.log('location', location)
  // console.log('location', location)

  // state, hook
  const [selectedCategory, setSelectedCategory] = useState(null)
  const category = useRecoilValue(categoryValue)

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
  }

  return (
    <form className="review-add-form" onSubmit={handleSubmitPlaceInfo}>
      <CategorySelectBox />
      <div>장소 이름 : {location.state.position.name}</div>
      <div>장소 주소 : {location.state.position.address}</div>
      <textarea
        className="description_text"
        name="description_text"
        maxLength={40}
        placeholder="장소에 대한 Comment를 입력해 주세요."
        required
      />
      <button type="submit" className="placeInfo-save-btn" disabled={false}>
        등록하기
      </button>
    </form>
  )
}
export default AddPlaceInfo

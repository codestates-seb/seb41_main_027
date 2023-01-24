import CategorySelectBox from './CategorySelectBox'
import { useLocation } from 'react-router-dom'
const AddPlaceInfo = () => {
  const location = useLocation()
  console.log('location', location)

  return (
    <div>
      <CategorySelectBox />
      <div>장소 이름 : {location.state.position.name}</div>
      <div>장소 주소 : {location.state.position.address}</div>
      <div>코멘트</div>
      <div>Tag</div>
      <button>등록하기</button>
    </div>
  )
}
export default AddPlaceInfo

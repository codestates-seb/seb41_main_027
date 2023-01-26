import { useNavigate } from 'react-router-dom'
import { ModalDimmed, InfoContent } from '../InfoModal/InfoModalStyle'
import AddPlaceInfo from '../../components/Map/AddPlaceInfo'
import { AddPlaceModalWrapper } from './AddPlaceModalStyle'
export const AddPlaceModal = () => {
  // state, hook
  const navigate = useNavigate()

  return (
    <ModalDimmed onClick={() => navigate(-1)}>
      <AddPlaceModalWrapper onClick={e => e.stopPropagation()}>
        <InfoContent>
          <AddPlaceInfo />
        </InfoContent>
      </AddPlaceModalWrapper>
    </ModalDimmed>
  )
}
export default AddPlaceModal

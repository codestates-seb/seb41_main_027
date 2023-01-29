import React, { useRef } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCopy } from '@fortawesome/free-regular-svg-icons'
import { toast } from 'react-toastify'

import { clipboardCopy } from '../../utils/common'
import { InfoTitle, ModalCloseBtn } from './TitleStyle'

const Title = ({ item, modalClose }) => {
  // console.log('-- (1)Title Render --')

  const { name, category, address } = item

  // hook
  const refAddress = useRef(null)

  // handle
  const handleClickCopy = () => {
    clipboardCopy(refAddress.current.innerText).then(() => {
      toast.info('주소가 복사되었습니다.')
    })
  }

  return (
    <InfoTitle>
      <div className="head-category-title">
        <div className="head-title">
          <span className="category">{category}</span>
          <p>{name}</p>
        </div>
        <ModalCloseBtn onClick={modalClose}>×</ModalCloseBtn>
      </div>
      <div className="head-address">
        <p ref={refAddress}>{address}</p>
        <button onClick={handleClickCopy}>
          <FontAwesomeIcon icon={faCopy} />
        </button>
      </div>
    </InfoTitle>
  )
}

export const MemoTitle = React.memo(Title)

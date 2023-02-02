import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCopy } from '@fortawesome/free-regular-svg-icons'
import { toast } from 'react-toastify'

import { CopyToClipboard } from 'react-copy-to-clipboard'
import { InfoTitle, ModalCloseBtn } from './TitleStyle'

const Title = ({ item, modalClose }) => {
  // console.log('-- (1)Title Render --')

  const { name, category, address } = item

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
        <p>{address}</p>
        <CopyToClipboard text={address} onCopy={() => toast.info('주소가 복사되었습니다.')}>
          <FontAwesomeIcon icon={faCopy} />
        </CopyToClipboard>
      </div>
    </InfoTitle>
  )
}

export const MemoTitle = React.memo(Title)

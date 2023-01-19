import React, { useRef } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCopy } from '@fortawesome/free-regular-svg-icons'
import { ClipBoardCopy } from '../../utils/common'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

const Title = props => {
  console.log('-- (1)Title Render --')

  const { name, category, address, description } = props.item

  // hook
  const refAddress = useRef(null)
  const navigate = useNavigate()

  // handle
  const handleClickCopy = () => {
    ClipBoardCopy(refAddress.current.innerText).then(() => {
      toast.info('주소가 복사되었습니다.')
    })
  }

  return (
    <>
      <div className="head-category-title">
        <div className="head-title">
          <span className="category">{category}</span>
          <p>{name}</p>
        </div>
        <button onClick={() => navigate(-1)}>×</button>
      </div>
      <div className="head-address">
        <p ref={refAddress}>{address}</p>
        <button onClick={handleClickCopy}>
          <FontAwesomeIcon icon={faCopy} />
        </button>
      </div>
    </>
  )
}

export const MemoTitle = React.memo(Title)

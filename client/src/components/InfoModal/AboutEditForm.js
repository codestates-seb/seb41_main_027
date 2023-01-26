import React, { useRef, useState } from 'react'
import { toast } from 'react-toastify'

import { InfoAboutEditForm } from './AboutEditFormStyle'
import * as placeApi from '../../api/place'
import { getLoginInfo } from '../../api/login'

const AboutEditForm = ({ item }) => {
  // console.log('-- (3)AboutEditForm Render --')

  const loginMemberId = getLoginInfo().id
  const { placeId, memberId, description } = item

  // state, hook
  const [isEdit, setIsEdit] = useState(false)
  const [prevAbout, setPrevAbout] = useState(description)
  const refAbout = useRef(null)

  // handle
  const handleClickEditBtn = () => {
    setIsEdit(true)
    refAbout.current.focus()
  }

  const handleSubmit = e => {
    e.preventDefault()

    // no change
    const aboutText = e.target.about_text.value
    if (aboutText === prevAbout) {
      toast.info('변경된 내용이 없습니다.')
      refAbout.current.focus()
      return
    }

    // db update
    refAbout.current.classList.remove('ani-border-twinkle')
    placeApi.updatePlaceDescription(placeId, { placeId, description: aboutText }).then(data => {
      setPrevAbout(data.description)
      refAbout.current.classList.add('ani-border-twinkle')
      setIsEdit(false)
    })
  }

  return (
    <InfoAboutEditForm onSubmit={handleSubmit}>
      <textarea
        ref={refAbout}
        name="about_text"
        placeholder="어떤 장소인지 소개해 주세요."
        readOnly={isEdit ? false : true}
        defaultValue={description}
        required
      />
      {loginMemberId === memberId && (
        <div className="body-about-modify">
          {!isEdit && <button onClick={handleClickEditBtn}>Edit</button>}
          {isEdit && (
            <>
              <button className="cancel-btn" onClick={() => setIsEdit(false)}>
                Cancel
              </button>
              <button type="submit">Save</button>
            </>
          )}
        </div>
      )}
    </InfoAboutEditForm>
  )
}

export const MemoAboutEditForm = React.memo(AboutEditForm)

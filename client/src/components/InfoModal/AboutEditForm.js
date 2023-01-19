import React, { useRef, useState } from 'react'
import { toast } from 'react-toastify'
import { updatePlaceDescription } from '../../api/place'

const AboutEditForm = ({ item }) => {
  console.log('-- (3)AboutEditForm Render --')

  const { placeId, description } = item

  const [isEdit, setIsEdit] = useState(false)
  const [prevAbout, setPrevAbout] = useState(description)
  const refAbout = useRef(null)

  const handleClickEdit = () => {
    setIsEdit(true)
    refAbout.current.focus()
  }

  const handleSubmit = e => {
    e.preventDefault()

    // no change
    const aboutText = e.target.about_text.value
    if (aboutText === prevAbout) {
      toast.info('수정된 내용이 없습니다.')
      refAbout.current.focus()
      return
    }

    // db update
    refAbout.current.classList.remove('ani-border-twinkle')
    updatePlaceDescription(placeId, { placeId, description: aboutText }).then(data => {
      setPrevAbout(data.description)
      refAbout.current.classList.add('ani-border-twinkle')
      setIsEdit(false)
    })
  }

  return (
    <form className="info-about-form" onSubmit={handleSubmit}>
      <textarea
        ref={refAbout}
        name="about_text"
        placeholder="어떤 장소인지 소개해 주세요."
        readOnly={isEdit ? false : true}
        defaultValue={description}
        required
      />
      <div className="body-about-modify">
        {!isEdit && <button onClick={handleClickEdit}>Edit</button>}
        {isEdit && (
          <>
            <button className="cancel-btn" onClick={() => setIsEdit(false)}>
              Cancel
            </button>
            <button type="submit">Save</button>
          </>
        )}
      </div>
    </form>
  )
}

export const MemoAboutEditForm = React.memo(AboutEditForm)

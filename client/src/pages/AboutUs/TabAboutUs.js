import styled from 'styled-components'
import { useState } from 'react'

const Wrapper = styled.div`
  width: 100%;
  font-size: 16px;
  font-weight: 400;
  line-height: 24px;
  a,
  a:hover,
  a:focus {
    text-decoration: none;
    transition: 0.5s;
    outline: none;
  }
  // 💄 layout ------------------
  .form-area {
    padding: 24px 32px;
    background-color: #fff;
    border: 1px solid #b8bccf;
    border-radius: 24px;
    box-shadow: 0px 2px 10px rgba(25, 1, 52, 0.12);
    /* padding: 30px; */
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .form-area .form-inner {
    width: 100%;
  }

  // 💄 tab --------------------
  .react-tab-pane {
    display: none;
  }
  .react-tab-pane.show {
    display: block;
  }
  .react-tab-link-group {
    margin-bottom: 24px;
    display: flex;
    align-items: center;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
  }
  .react-tab-link-group li {
    margin-right: 10px;
    list-style: none;
  }
  .react-tab-link-group li .react-tab-link {
    display: inline-block;
    padding: 6px 15px;
    font-size: 16px;
    font-weight: 500;
    color: rgba(0, 0, 0, 0.7);
    cursor: pointer;
    border-radius: 8px;
  }
  .react-tab-link-group li .react-tab-link:hover {
    color: #17ac52;
    /* color: #fff; */
  }
  .react-tab-link-group li.active .react-tab-link {
    color: #17ac52;
    /* color: #fff; */
    background-color: rgba(19, 197, 124, 0.2);
    /* background-color: rgba(255, 255, 255, 0.2); */
  }
  .react-tab-pane p {
    padding: 0 12px;
    white-space: pre-line;
  }
  .react-tab-pane h3 {
    margin-bottom: 15px;
    font-size: 20px;
    font-weight: 500;
  }
  .vertical-tabs.react-tab-link-group li {
    width: 100%;
  }
  .vertical-tabs.react-tab-link-group li .react-tab-link {
    width: 100%;
  }
`
const TabAboutUs = () => {
  // 🤖 tab State ------------------------
  const [active, setActive] = useState(0)
  const tabClick = e => {
    const index = parseInt(e.target.id, 0)
    console.log(index)
    if (index !== active) {
      setActive(index)
    }
  }

  return (
    <Wrapper>
      <div className="form-area">
        <div className="form-inner">
          <ul className="react-tab-link-group">
            <li className={`${active === 0 ? 'active' : ''}`}>
              <button className="react-tab-link" onClick={tabClick} id="0">
                성승훈
              </button>
            </li>
            <li className={`${active === 1 ? 'active' : ''}`}>
              <button className="react-tab-link" onClick={tabClick} id="1">
                최지민
              </button>
            </li>
            <li className={`${active === 2 ? 'active' : ''}`}>
              <button className="react-tab-link" onClick={tabClick} id="2">
                황원용
              </button>
            </li>
            <li className={`${active === 3 ? 'active' : ''}`}>
              <button className="react-tab-link" onClick={tabClick} id="3">
                임기정
              </button>
            </li>
            <li className={`${active === 4 ? 'active' : ''}`}>
              <button className="react-tab-link" onClick={tabClick} id="4">
                박승필
              </button>
            </li>
            <li className={`${active === 5 ? 'active' : ''}`}>
              <button className="react-tab-link" onClick={tabClick} id="5">
                강보미
              </button>
            </li>
          </ul>
          <div className="react-tab-content">
            <div className={`react-tab-pane ${active === 0 ? 'show' : ''}`}>
              <p>성승훈</p>
            </div>
            <div className={`react-tab-pane ${active === 1 ? 'show' : ''}`}>
              <p>최지민</p>
            </div>
            <div className={`react-tab-pane ${active === 2 ? 'show' : ''}`}>
              <p>황원용</p>
            </div>
            <div className={`react-tab-pane ${active === 3 ? 'show' : ''}`}>
              <p>임기정</p>
            </div>
            <div className={`react-tab-pane ${active === 4 ? 'show' : ''}`}>
              <p>박승필</p>
            </div>
            <div className={`react-tab-pane ${active === 5 ? 'show' : ''}`}>
              <p>강보미</p>
            </div>
          </div>
        </div>
      </div>
    </Wrapper>
  )
}

export default TabAboutUs

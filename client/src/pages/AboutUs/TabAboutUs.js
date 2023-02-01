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
              <p>
                에코그린서울을 서비스를 만들면서 친환경 제품들에 대해 많은 관심이 생기게 되었습니다. <br />
                여러분들도 에코그린서울을 통해 주변의 친환경 관련 장소를 경험해보세요.
              </p>
            </div>
            <div className={`react-tab-pane ${active === 1 ? 'show' : ''}`}>
              <p>여러분이 알고 계신 좋은 장소들을 공유하여 지도를 예쁘게 꾸며보세요~🙂</p>
            </div>
            <div className={`react-tab-pane ${active === 2 ? 'show' : ''}`}>
              <p>저희 서비스에 관심을 가지고 계신 투자자분들의 연락을 기다리고 있습니다.</p>
            </div>
            <div className={`react-tab-pane ${active === 3 ? 'show' : ''}`}>
              <p>
                친환경에 대해 무겁게만 생각했었는데 아주 사소한 실천이 큰 환경을 만들 수 있다는 것을 프로젝트를 통해
                배운 것 같습니다.
                <br /> 나만의 장소가 아닌 모두의 장소로 만들어 공공의 가치가 존재하는 공간이 되기를 희망합니다.
              </p>
            </div>
            <div className={`react-tab-pane ${active === 4 ? 'show' : ''}`}>
              <p>나만의 숨은 장소를 다른 사람과 공유해보아요!</p>
            </div>
            <div className={`react-tab-pane ${active === 5 ? 'show' : ''}`}>
              <p>
                “지구에게 인간이 미안해!!”
                <br />
                평소 비건푸드에 관심이 있고, 어떤 소비를 하든 부가적인 포장 등의 쓰레기가나오는 것에 죄책감이 들어
                신경쓰고있습니다.
                <br />그 때 마다 비건 블로거나 친환경 가게 포스팅을 통해 광고인지, 정말 괜찮은 곳인지 찾아 내곤 했던
                불편함들이 생각납니다. <br />
                ‘환경을 위해서 소비자가 들여야하는 정신적 피로를 누군가 해결해주어야 친환경 관련 시장의 파이가
                커질텐데…’
                <br />
                개인이 아닌 사회가 변화되는 그 날까지! <br />
                에코그린서울이 여러분의 피로를 줄여줄 수 있는 하나의 방안이 될 수 있기를 바랍니다.
              </p>
            </div>
          </div>
        </div>
      </div>
    </Wrapper>
  )
}

export default TabAboutUs

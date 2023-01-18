import styled from 'styled-components'
import { useRecoilState } from 'recoil'
import { listClick } from '../../../recoil/atoms'

const Wrapper = styled.div`
  // Style 💄
  width: 260px;
  height: 100px !important;
  padding: 16px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  background: #fff;
  box-shadow: 0px 4px 10px rgba(25, 1, 52, 0.4);
  /* box-shadow: 0px 4px 10px rgba(25, 1, 52, 0.16); */
  border-radius: 12px;
  // Common
  p {
    display: flex;
    align-items: center;
    letter-spacing: -0.5px;
  }
  p:first-child {
    justify-content: space-between;
  }
  p:nth-child(2) {
    justify-content: flex-start;
    gap: 8px;
  }

  .card-title {
    margin-bottom: 8px; // Demo Position 🫡
    align-items: flex-start;
    font-weight: 500;
    font-size: 16px;
    line-height: 20px;

    .site-name {
      width: 168px;
      height: 40px;
    }
  }

  .view-like {
    display: flex;
    align-items: center;
    justify-content: center;
    .ico-like {
      color: #ff3838;
      margin-right: 4px;
    }
    .count-like {
      font-size: 14px;
      font-weight: 400;
      line-height: 17px;
    }
  }

  .tag-category {
    width: 40px;
    height: 20px;
    box-sizing: border-box;
    padding: 2px 8px;
    color: #ffffff;
    font-weight: 400;
    font-size: 14px;
    line-height: 17px;
    background: #13c57c;
    box-shadow: 0px 0px 2px rgba(0, 0, 0, 0.15);
    border-radius: 4px;
  }

  .site-addr {
    font-weight: 400;
    font-size: 14px;
    line-height: 17px;
    color: #909499;
  }
`

const SiteInfoCard = ({ positions }) => {
  const [clickPoint, setClickPoint] = useRecoilState(listClick)

  return (
    <Wrapper
      onClick={() => {
        setClickPoint(positions.latlng || positions.position)
        console.log(clickPoint)
      }}
    >
      <p className="card-title">
        <h1 className="site-name">{positions.name}</h1>
        <div className="view-like">
          <span className="ico-like">♥️</span>
          {/* {삼항연산자를 사용해서 display:none 속성?} */}
          {/* {islike ? <span className="count-like">000</span> : null} */}
          <span className="count-like">000</span>
        </div>
      </p>
      <p>
        <span className="tag-category">카페</span>
        <p className="site-addr">{positions.address}</p>
      </p>
    </Wrapper>
  )
}

export default SiteInfoCard

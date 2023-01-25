import styled from 'styled-components'
import { useRecoilState } from 'recoil'
import { addPlaceInfo, listClick } from '../../../recoil/atoms'
import { Link, useLocation } from 'react-router-dom'

const Wrapper = styled.li`
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
  div {
    display: flex;
    align-items: center;
    letter-spacing: -0.5px;
  }
  p {
    display: flex;
    align-items: center;
  }
  /* p {
    justify-content: flex-start;
    gap: 8px;
  } */

  .card-title {
    margin-bottom: 8px; // Demo Position 🫡
    align-items: flex-start;
    font-weight: 500;
    font-size: 16px;
    line-height: 20px;

    .site-name {
      font-weight: bold;
      color: #0581bb;
      width: 168px;
      height: 40px;
    }

    .add-place {
      color: #ff3838;
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
    padding: 10px;
    font-weight: 400;
    font-size: 14px;
    line-height: 17px;
    color: #909499;
  }
`

const SiteInfoCard = ({ positions, index }) => {
  const [clickPoint, setClickPoint] = useRecoilState(listClick)

  const location = useLocation()
  return (
    <Wrapper
      onClick={() => {
        setClickPoint(positions.latlng || positions.position || { lat: positions.latitude, lng: positions.longitude })
        // setClickPoint(positions.position)
        // console.log('key:', key)
        // console.log(clickPoint)
        // console.log('position : ', positions)
        // console.log('index : ', index)
      }}
    >
      <div className="card-title">
        <h1 className="site-name">{positions.name || positions.title}</h1>

        {positions && positions.likeCount >= 0 ? (
          <div className="view-like">
            <span className="ico-like">♥️</span>
            <span className="count-like">{positions.likeCount}</span>
          </div>
        ) : (
          <div className="add-place">
            <Link to="/addPlace" state={{ bgLocation: location, position: positions }}>
              등록하기
            </Link>
          </div>
        )}
      </div>
      <div>
        <span className="tag-category">주소</span>
        <p className="site-addr">{positions.address}</p>
      </div>
    </Wrapper>
  )
}

export default SiteInfoCard

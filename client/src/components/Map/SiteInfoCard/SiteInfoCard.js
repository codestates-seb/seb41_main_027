import styled from 'styled-components'
import { useRecoilState } from 'recoil'
import { addPlaceInfo, listClick } from '../../../recoil/atoms'
import { Link, useLocation } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowUp, faSquarePlus } from '@fortawesome/free-solid-svg-icons'

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
  // CTA btn
  .add-place {
    color: #ff3838;
  }

  .card-title {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
    font-weight: 500;
    font-size: 16px;
    line-height: 20px;

    .site-name {
      width: 100%;
      height: 40px;
      color: #5f697d;
      font-weight: 600;
    }

    .view-like {
      display: flex;
      align-items: center;
      justify-content: center;
      line-height: 16px;
      gap: 2px;
      .ico-like {
        color: #ff3838;
        font-size: 16px;
      }
      .count-like {
        font-size: 14px;
        font-weight: 500;
      }
    }

    .more-info {
      margin-left: 8px;
      font-size: 16px;
      color: #909499;
      :hover {
        color: #13c57c;
      }
    }
  }

  .tag-category {
    margin-right: 4px;
    width: 40px;
    height: 20px;
    box-sizing: border-box;
    padding: 2px 8px;
    color: #ffffff;
    font-weight: 400;
    font-size: 14px;
    letter-spacing: -0.8px;
    line-height: 17px;
    background: #13c57c;
    box-shadow: 0px 0px 2px rgba(0, 0, 0, 0.15);
    border-radius: 4px;
  }

  .site-addr {
    display: flex;
    align-items: flex-start;
    font-weight: 400;
    width: 176px;
    font-size: 14px;
    line-height: 17px;
    color: #909499;
    letter-spacing: -0.8px;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: keep-all;
  }
`

const SiteInfoCard = ({ positions, index }) => {
  const [clickPoint, setClickPoint] = useRecoilState(listClick)

  const location = useLocation()
  return (
    <Wrapper
      onClick={() => {
        setClickPoint(positions.latlng || positions.position || { lat: positions.latitude, lng: positions.longitude })
      }}
    >
      <div className="card-title">
        <h1 className="site-name">{positions.name || positions.title}</h1>

        {positions && positions.likeCount >= 0 ? (
          <div>
            <div className="view-like">
              <span className="ico-like">♥️</span>
              <span className="count-like">{positions.likeCount}</span>
            </div>
            <div className="more-info">
              <Link to={`/` + positions.placeId} state={{ bgLocation: location }}>
                <FontAwesomeIcon icon={faArrowUp} transform={{ rotate: 45 }} />
              </Link>
            </div>
          </div>
        ) : (
          <div className="add-place">
            <Link to="/addPlace" state={{ bgLocation: location, position: positions }}>
              <FontAwesomeIcon icon={faSquarePlus} />
            </Link>
          </div>
        )}
      </div>
      <div>
        <span className="tag-category">{positions.category ? positions.category.slice(0, 2) : '주소'}</span>
        <p className="site-addr">{positions.address}</p>
      </div>
    </Wrapper>
  )
}

export default SiteInfoCard

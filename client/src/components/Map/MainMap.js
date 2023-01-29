import styled from 'styled-components'
import { MapMarker, Map, CustomOverlayMap } from 'react-kakao-maps-sdk'
import { useRef, useState } from 'react'
import { useRecoilState, useResetRecoilState } from 'recoil'
import { listClick, searchValue, placesAll } from '../../recoil/atoms'
import SearchBar from './SearchBar/SearchBar'
import SiteInfoCard from './SiteInfoCard/SiteInfoCard'
import { useGetPlace, useKeywordSearch } from '../../query/place'
import Loading from '../Loading/Loading'
import { toast } from 'react-toastify'
import { Link, useLocation } from 'react-router-dom'

const Container = styled.section`
  position: relative;
  z-index: 500;
  overflow: hidden;
  box-sizing: border-box;
  height: calc(100% - 88px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;

  .site-list {
    position: absolute;
    z-index: 1500;
    // Demo Position 🫡
    top: 140px;
    right: 32px;
    width: inherit;
    height: 71.5%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    gap: 8px;
    overflow-y: auto;
    // 🚧 scroll Cut-off shadow 처리용
    margin: 0px -32px;
    padding: 0px 32px;
    -ms-overflow-style: none; // IE
    &::-webkit-scrollbar {
      display: none !important; // etc
    }
    border-radius: 12px;
  }
`
const MarkerInfoBox = styled.div`
  color: black;
`
const { kakao } = window

const MainMap = ({ sort, id }) => {
  const mapRef = useRef()
  const [map, setMap] = useState()
  const location = useLocation()

  // state, hook
  const [clickPoint, setClickPoint] = useRecoilState(listClick)
  const [keyword, setKeyword] = useRecoilState(searchValue)
  const [points, setPoints] = useRecoilState(placesAll)
  console.log('keyword : ', keyword)
  // fetch data
  if (keyword === '') {
    const query = useGetPlace(sort, id)
    if (query.isLoading) return <Loading />
    if (query.isError) return toast.error(query.error.message)
    const items = query.data
    setPoints(items.placeList)

    console.log('points', points)
    console.log('sort : ', sort)
    console.log('categoryId : ', id)
  } else {
    const query = useKeywordSearch(keyword)
    if (query.isLoading) return <Loading />
    if (query.isError) return toast.error(query.error.message)
    const items = query.data
    console.log('keywordSearch : ', items)
    setPoints(items.placeList)
  }
  return (
    <Container>
      <SearchBar />
      <div className="site-list">
        {points.map((point, index) => (
          <SiteInfoCard index={index} key={index} positions={point} />
        ))}
      </div>
      <Map // 지도를 표시할 Container
        center={clickPoint}
        style={{
          width: '100%',
          height: '100%',
        }}
        level={7} // 지도의 확대 레벨
        ref={mapRef}
        onCreate={setMap}
      >
        {points.map((point, index) => (
          <>
            <CustomOverlayMap position={{ lat: point.latitude, lng: point.longitude }} placeInfo={point} yAnchor={2.3}>
              <div
                className="customoverlay"
                style={{
                  padding: '10px 20px',
                  borderRadius: '18px',
                  backgroundColor: 'white',
                  color: '#0581BB',
                }}
              >
                <Link to={`/` + point.placeId} state={{ bgLocation: location }}>
                  <span className="title">{point.name}</span>
                </Link>
              </div>
            </CustomOverlayMap>
            <MapMarker
              key={`point-${point.content}-${point.latitude},${point.longitude}`}
              position={{ lat: point.latitude, lng: point.longitude }}
              onClick={point => {
                map.panTo(point.getPosition())
              }}
            ></MapMarker>
          </>
        ))}
      </Map>
    </Container>
  )
}
export default MainMap

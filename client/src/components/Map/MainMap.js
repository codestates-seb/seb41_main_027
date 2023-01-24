import styled from 'styled-components'
import { MapMarker, Map, useMap, CustomOverlayMap } from 'react-kakao-maps-sdk'
import { useRef, useMemo, useState, useEffect } from 'react'
import { useRecoilState } from 'recoil'
import { listClick, placesAll } from '../../recoil/atoms'

import SearchBar from './SearchBar/SearchBar'
import SiteInfoCard from './SiteInfoCard/SiteInfoCard'

import { useGetPlace } from '../../query/place'
import Loading from '../Loading/Loading'
import { toast } from 'react-toastify'

const Container = styled.section`
  position: relative;
  z-index: 500;
  overflow: hidden;
  box-sizing: border-box;
  height: calc(100% - 94px);
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
    height: 100%;
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

const MainMap = ({ sort }) => {
  const mapRef = useRef()
  const [map, setMap] = useState()
  // state, hook

  // 중심 좌표로 랜더링 시작하기
  useEffect(() => {
    const map = mapRef.current
    if (map) map.setBounds(bounds)
    // console.log('bound : ' + bounds)
  }, [])
  const [clickPoint, setClickPoint] = useRecoilState(listClick)

  // listItem 클릭시 해당위치 지도에서 정중앙으로 이동!!!! 추가로 마커색 변환 및 인포필요할듯.
  useEffect(() => {
    function panTo() {
      const map = mapRef.current
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(clickPoint)

      map.panTo(moveLatLon)
    }
  }, [clickPoint])

  // const [places, setPlaces] = useRecoilState(placesAll)
  // fetch data
  const query = useGetPlace(sort)
  if (query.isLoading) return <Loading />
  if (query.isError) return toast.error(query.error.message)
  const item = query.data
  console.log('되나? :', item)
  // const query = useGetPlace()
  // console.log('query : ', query)

  // 장소 list 가져오기 이상없음..

  const [points, setPoints] = useState([
    { title: '맛집', latlng: { lat: 33.452278, lng: 126.567803 }, isPanto: false, address: '제주도', islikeCnt: 100 },
    { title: '카카오집', latlng: { lat: 33.452671, lng: 126.574792 }, isPanto: false },
    { title: '카페', latlng: { lat: 33.451744, lng: 126.572441 }, isPanto: false },
  ])

  const bounds = useMemo(() => {
    const bounds = new kakao.maps.LatLngBounds()

    points.forEach(point => {
      bounds.extend(new kakao.maps.LatLng(point.latlng.lat, point.latlng.lng))
    })
    return bounds
  }, [points])

  const [selectedMarker, setSeleteMarker] = useState()

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
        level={8} // 지도의 확대 레벨
        ref={mapRef}
        onCreate={setMap}
      >
        {points.map((point, index) => (
          <>
            <CustomOverlayMap position={point.latlng} yAnchor={2.3}>
              <div
                className="customoverlay"
                style={{
                  padding: '10px 20px',
                  borderRadius: '18px',
                  backgroundColor: 'white',
                  color: '#0581BB',
                }}
              >
                <a href=" " target="_blank" rel="noreferrer">
                  <span className="title">{point.title}</span>
                </a>
              </div>
            </CustomOverlayMap>

            <MapMarker
              key={`point-${point.content}-${point.latlng.lat},${point.latlng.lng}`}
              position={point.latlng}
              onClick={point => {
                setSeleteMarker(point)
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

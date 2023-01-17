import styled from 'styled-components'
import { MapMarker, Map, useMap } from 'react-kakao-maps-sdk'
import { useRef, useMemo, useState, useEffect } from 'react'
import { useRecoilState } from 'recoil'
import { listClick } from '../../recoil/atoms'

import SearchBar from './SearchBar/SearchBar'
import SiteInfoCard from './SiteInfoCard/SiteInfoCard'

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

const { kakao } = window

const MainMap = () => {
  const MARKER_WIDTH = 33 // 기본, 클릭 마커의 너비
  const MARKER_HEIGHT = 36 // 기본, 클릭 마커의 높이
  const OFFSET_X = 12 // 기본, 클릭 마커의 기준 X좌표
  const OFFSET_Y = MARKER_HEIGHT // 기본, 클릭 마커의 기준 Y좌표
  const OVER_MARKER_WIDTH = 40 // 오버 마커의 너비
  const OVER_MARKER_HEIGHT = 42 // 오버 마커의 높이
  const OVER_OFFSET_X = 13 // 오버 마커의 기준 X좌표
  const OVER_OFFSET_Y = OVER_MARKER_HEIGHT // 오버 마커의 기준 Y좌표
  const SPRITE_MARKER_URL = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites2.png' // 스프라이트 마커 이미지 URL
  const SPRITE_WIDTH = 126 // 스프라이트 이미지 너비
  const SPRITE_HEIGHT = 146 // 스프라이트 이미지 높이
  const SPRITE_GAP = 10 // 스프라이트 이미지에서 마커간 간격

  const mapRef = useRef()

  const [points, setPoints] = useState([
    { title: '맛집', latlng: { lat: 33.452278, lng: 126.567803 }, isPanto: false },
    { title: '카카오집', latlng: { lat: 33.452671, lng: 126.574792 }, isPanto: false },
    { title: '카페', latlng: { lat: 33.451744, lng: 126.572441 }, isPanto: false },
  ])

  // const [bound, setBound] = useState([])

  const bounds = useMemo(() => {
    const bounds = new kakao.maps.LatLngBounds()

    points.forEach(point => {
      bounds.extend(new kakao.maps.LatLng(point.latlng.lat, point.latlng.lng))
    })
    return bounds
  }, [points])

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

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      map.panTo(moveLatLon)
    }
  }, [clickPoint])

  // 마커 모양 Click 변경 구현
  const EventMarkerContainer = ({ position, index, onClick, isClicked }) => {
    const map = useMap()
    const [isOver, setIsOver] = useState(false)
    const gapX = MARKER_WIDTH + SPRITE_GAP // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
    const originY = (MARKER_HEIGHT + SPRITE_GAP) * index // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
    const overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * index // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
    const normalOrigin = { x: 0, y: originY } // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
    const clickOrigin = { x: gapX, y: originY } // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
    const overOrigin = { x: gapX * 2, y: overOriginY } // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표

    let spriteOrigin = isOver ? overOrigin : normalOrigin

    if (isClicked) {
      spriteOrigin = clickOrigin
    }

    return (
      <MapMarker
        position={position} // 마커를 표시할 위치
        onClick={onClick}
        onMouseOver={() => setIsOver(true)}
        onMouseOut={() => setIsOver(false)}
        image={{
          src: SPRITE_MARKER_URL,
          size: {
            width: MARKER_WIDTH,
            height: MARKER_HEIGHT,
          },
          options: {
            offset: {
              x: OFFSET_X,
              y: OFFSET_Y,
            },
            spriteSize: {
              width: SPRITE_WIDTH,
              height: SPRITE_HEIGHT,
            },
            spriteOrigin: spriteOrigin,
          },
        }}
      ></MapMarker>
    )
  }
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
        // center={{ lat: bounds.qa, lng: bounds.ha }}
        center={clickPoint}
        // isPanto={clickPoint.isPanto}
        style={{
          width: '100%',
          height: '100%',
        }}
        level={8} // 지도의 확대 레벨
        ref={mapRef}
      >
        {points.map((point, index) => (
          <EventMarkerContainer
            index={index}
            key={`EventMarkerContainer-${point.latlng.lat}-${point.latlng.lng}`}
            position={point.latlng}
            onClick={() => setSeleteMarker(index)}
            isClicked={selectedMarker === index}
          />
        ))}
      </Map>
    </Container>
  )
}
export default MainMap

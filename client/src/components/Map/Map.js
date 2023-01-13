import styled from 'styled-components'
import { MapMarker, Map } from 'react-kakao-maps-sdk'
import { useRef, useMemo, useState, useEffect } from 'react'

import SearchBar from './SearchBar/SearchBar'
import SiteInfoCard from './SiteInfoCard/SiteInfoCard'

const Container = styled.section`
  position: relative;
  z-index: 500;
  overflow: hidden;
  box-sizing: border-box;
  height: calc(100% - 88px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.6);
  /* box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4); */
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

const Main = () => {
  const mapRef = useRef()
  const [points, setPoints] = useState([
    { lat: 33.452278, lng: 126.567803 },
    { lat: 33.452671, lng: 126.574792 },
    { lat: 33.451744, lng: 126.572441 },
  ])

  const bounds = useMemo(() => {
    const bounds = new kakao.maps.LatLngBounds()

    points.forEach(point => {
      bounds.extend(new kakao.maps.LatLng(point.lat, point.lng))
    })
    return bounds
  }, [points])
  useEffect(() => {
    const map = mapRef.current
    if (map) map.setBounds(bounds)
  }, [points])

  return (
    <Container>
      <SearchBar />
      <div className="site-list">
        <SiteInfoCard />
      </div>
      <Map // 지도를 표시할 Container
        center={{ lat: 37.542668, lng: 126.976395 }}
        style={{
          width: '100%',
          height: '100%',
        }}
        level={6} // 지도의 확대 레벨
        ref={mapRef}
      >
        {points.map(point => (
          <MapMarker key={`${point.lat}-${point.lng}`} position={point} />
        ))}
      </Map>
      <button
        onClick={() => {
          const map = mapRef.current
          if (map) map.setBounds(bounds)
        }}
      >
        지도 범위 재설정 하기
      </button>
    </Container>
  )
}
export default Main

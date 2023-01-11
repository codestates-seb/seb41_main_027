import styled from 'styled-components'
import { MapMarker, Map } from 'react-kakao-maps-sdk'
import { useRef, useMemo, useState, useEffect } from 'react'

const Container = styled.section`
  z-index: 1000;
  overflow: hidden;
  height: calc(100% - 100px);
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;
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
      <Map // 지도를 표시할 Container
        center={{ lat: 37.542668, lng: 126.976395 }}
        style={{
          width: '100%',
          height: '450px',
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

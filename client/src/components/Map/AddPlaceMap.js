import styled from 'styled-components'
import { MapMarker, Map, useMap } from 'react-kakao-maps-sdk'
import { useRef, useMemo, useState, useEffect } from 'react'
import { useRecoilState } from 'recoil'
import { listClick, searchValue } from '../../recoil/atoms'

import SearchBar from './SearchBar/SearchBar'
import SiteInfoCard from './SiteInfoCard/SiteInfoCard'
import SearchInput from './SearchBar/SearchInput'

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
const Wrapper = styled.div`
  // Position 🫡
  position: absolute;
  z-index: 9000;
  left: 50%;
  transform: translate(-50%, 0%);
  display: flex;
  margin-top: 40px;
  gap: 16px;
  justify-content: center;
`
const { kakao } = window

const AddPlaceMap = () => {
  const [info, setInfo] = useState() //info 창 정보
  const [markers, setMarkers] = useState([])
  const [map, setMap] = useState()

  const mapRef = useRef()
  const [keyword, setKeyword] = useRecoilState(searchValue)
  useEffect(() => {
    if (!map) return
    // if (!keyword.replace(/^\s+|\s+$/g, '')) {
    //   alert('키워드를 입력해주세요!')
    //   return false
    // }
    const ps = new kakao.maps.services.Places()
    ps.keywordSearch(keyword, (data, status, _pagination) => {
      console.log(keyword)
      if (status === kakao.maps.services.Status.OK) {
        const bounds = new kakao.maps.LatLngBounds()
        let markers = []

        for (let i = 0; i < data.length; i++) {
          console.log(data[i])
          markers.push({
            position: {
              lat: Number(data[i].y),
              lng: Number(data[i].x),
            },
            name: data[i].place_name,
            address: data[i].address_name,
            id: data[i].id,
          })
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x))
        }
        setMarkers(markers)
        map.setBounds(bounds)
        // console.log(markers)
      }
    })
  }, [keyword])

  const [clickPoint, setClickPoint] = useRecoilState(listClick)

  useEffect(() => {
    function panTo() {
      const map = mapRef.current
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(clickPoint)

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      map.panTo(moveLatLon)
      console.log(clickPoint)
      console.log(typeof clickPoint)
    }
  }, [clickPoint])

  return (
    <Container>
      <Wrapper>
        <SearchInput />
      </Wrapper>
      <div className="site-list">
        {markers.map((point, index) => (
          <SiteInfoCard index={index} key={index} positions={point} />
        ))}
      </div>
      <Map // 지도를 표시할 Container
        // center={{ lat: bounds.qa, lng: bounds.ha }}
        // center={{ lat: 37.566826, lng: 126.9786567 }}
        center={clickPoint}
        // isPanto={clickPoint.isPanto}
        style={{
          width: '100%',
          height: '100%',
        }}
        level={5} // 지도의 확대 레벨
        ref={mapRef}
        onCreate={setMap}
      >
        {markers.map(marker => (
          <MapMarker
            key={`marker-${marker.content}-${marker.position.lat},${marker.position.lng}`}
            position={marker.position}
            onClick={() => setInfo(marker)}
          >
            {info && info.name === marker.name && <div style={{ color: '#000' }}>{marker.name}</div>}
          </MapMarker>
        ))}
      </Map>
    </Container>
  )
}
export default AddPlaceMap

import { StaticMap } from 'react-kakao-maps-sdk'

const DrawStaticMap = props => {
  const { w, h, lat, lng, label, level } = props
  return (
    <StaticMap
      center={{
        lat: lat,
        lng: lng,
      }}
      style={{
        width: w,
        height: h,
      }}
      marker={[
        {
          position: {
            lat: lat,
            lng: lng,
          },
        },
        {
          position: {
            lat: lat,
            lng: lng,
          },
          text: label,
        },
      ]}
      level={level}
    />
  )
}

export default DrawStaticMap

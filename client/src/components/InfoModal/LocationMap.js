import React from 'react'
import DrawStaticMap from '../Map/StaticMap'

const LocationMap = props => {
  // console.log('-- (5)LocationMap Render --')

  const { name, latitude, longitude } = props.item

  const staticMapInfo = {
    w: '100%',
    h: '100%',
    lat: latitude,
    lng: longitude,
    label: name,
    level: 4,
  }

  return (
    <section className="map">
      <h3>여기에 있어요</h3>
      <div className="map-static">{DrawStaticMap(staticMapInfo)}</div>
    </section>
  )
}

export const MemoLocationMap = React.memo(LocationMap)

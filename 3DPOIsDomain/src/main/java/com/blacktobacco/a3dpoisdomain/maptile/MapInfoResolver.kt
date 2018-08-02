package com.blacktobacco.a3dpoisdomain.maptile

import rx.Observable
import javax.inject.Inject

class MapInfoResolver @Inject constructor(val poIsRepository: POIsRepository) {

  fun getMapTile() =
      Observable.just(MapTile("Rome",
          GeoPoint(41.89185, 12.4906),
          0.0067340000000000004f,
          ReferencePoint(GeoPoint(41.89007, 12.49344), VertexPoint(235.84084f, -197.85388f))
      ))

  fun getPOI(position: GeoPoint) = poIsRepository.getPOI(position)
}
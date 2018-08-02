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
  /*.flatMap {  Observable.from(listOf(
      POI(GeoPoint(41.8928315, 12.4929574), "Cafe Coldel Paola"),
      POI(GeoPoint(41.891496, 12.4927855), "Oppio Cafe"),
      POI(GeoPoint(41.89123, 12.48984), "Escaleras"),
      POI(GeoPoint(41.8933142, 12.4887178), "Gustando Roma"))) }*/

}
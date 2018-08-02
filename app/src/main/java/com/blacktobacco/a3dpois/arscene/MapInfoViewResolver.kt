package com.blacktobacco.a3dpois.arscene

import com.blacktobacco.a3dpoisdomain.maptile.MapInfoResolver
import javax.inject.Inject

class MapInfoViewResolver @Inject constructor(
    private val mapInfoResolver: MapInfoResolver
) {

  fun getMapInfo() =
      mapInfoResolver.getMapTile()
          .map { it.toMapInfoView() }

  fun getPOI3D(mapInfoView: MapInfoView) =
      mapInfoResolver.getPOI(mapInfoView.center)
          .map { it.toPOI3D(mapInfoView.center, mapInfoView.referencePoint, mapInfoView.scale) }

}
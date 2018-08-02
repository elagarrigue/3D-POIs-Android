package com.blacktobacco.a3dpois.arscene

import com.blacktobacco.a3dpois.R
import com.blacktobacco.a3dpoisdomain.maptile.GeoPoint
import com.blacktobacco.a3dpoisdomain.maptile.MapTile
import com.blacktobacco.a3dpoisdomain.maptile.POI
import com.blacktobacco.a3dpoisdomain.maptile.ReferencePoint
import com.google.ar.sceneform.math.Vector3
import kotlin.math.abs

fun MapTile.toMapInfoView(): MapInfoView =
    MapInfoView(
        when (name) {
          "Rome" -> R.raw.rome7
          else -> -1
        },
        center,
        scale,
        referencePoint
    )

fun POI.toPOI3D(center: GeoPoint, referencePoint: ReferencePoint, scale: Float): POI3D {

  var x = abs(center.long - position.long) * referencePoint.vertexPosition.x / abs(center.long - referencePoint.position.long)
  var y = abs(center.lat - position.lat) * referencePoint.vertexPosition.y / abs(center.lat - referencePoint.position.lat)

  if(center.lat > position.lat) y *= -1
  if(center.long > position.long) x *= -1

  return POI3D(Vector3(x.toFloat() * scale,
      0.1f,
      y.toFloat() * scale), info)
}

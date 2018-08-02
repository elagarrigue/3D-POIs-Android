package com.blacktobacco.a3dpois.arscene

import com.blacktobacco.a3dpoisdomain.maptile.GeoPoint
import com.blacktobacco.a3dpoisdomain.maptile.ReferencePoint
import com.google.ar.sceneform.math.Vector3

data class POI3D(val position: Vector3, val label: String)
data class MapInfoView(val mapRes: Int, val center: GeoPoint, val scale: Float, val referencePoint: ReferencePoint)
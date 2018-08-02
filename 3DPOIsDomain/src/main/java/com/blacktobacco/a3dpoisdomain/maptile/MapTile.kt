package com.blacktobacco.a3dpoisdomain.maptile

data class GeoPoint(val lat: Double, val long: Double)
data class VertexPoint(val x: Float, val y: Float)
data class ReferencePoint(val position: GeoPoint, val vertexPosition: VertexPoint)
data class POI(val position: GeoPoint, val info: String = "")
data class MapTile(val name: String, val center: GeoPoint, val scale: Float, val referencePoint: ReferencePoint)
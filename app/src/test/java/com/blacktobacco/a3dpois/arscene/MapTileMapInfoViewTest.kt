package com.blacktobacco.a3dpois.arscene

import com.blacktobacco.a3dpoisdomain.maptile.GeoPoint
import com.blacktobacco.a3dpoisdomain.maptile.POI
import com.blacktobacco.a3dpoisdomain.maptile.ReferencePoint
import com.blacktobacco.a3dpoisdomain.maptile.VertexPoint
import org.amshove.kluent.`should be equal to`
import org.junit.Test

class MapTileMapInfoViewTest {

  @Test
  fun `map real world POI position to 3D vertex in 1st quadrant`() {
    // Arrange
    val poi = POI(GeoPoint(41.8928315, 12.4929574))
    val center = GeoPoint(41.89185, 12.4906)
    val referencePoint = ReferencePoint(GeoPoint(41.89007, 12.49344), VertexPoint(235.84048f, -197.85388f))
    val scale = 1f

    // Act
    val poi3D = poi.toPOI3D(center, referencePoint, scale)

    // Assert
    poi3D.position.x.toInt() `should be equal to` 195
    poi3D.position.z.toInt() `should be equal to` -109
  }

  @Test
  fun `map real world POI position to 3D vertex in 2nd quadrant`() {
    // Arrange
    val poi = POI(GeoPoint(41.891496, 12.4927855))
    val center = GeoPoint(41.89185, 12.4906)
    val referencePoint = ReferencePoint(GeoPoint(41.89007, 12.49344), VertexPoint(235.84048f, -197.85388f))
    val scale = 1f

    // Act
    val poi3D = poi.toPOI3D(center, referencePoint, scale)

    // Assert
    poi3D.position.x.toInt() `should be equal to` 181
    poi3D.position.z.toInt() `should be equal to` 39
  }

  @Test
  fun `map real world POI position to 3D vertex in 3rd quadrant`() {
    // Arrange
    val poi = POI(GeoPoint(41.89123, 12.48984))
    val center = GeoPoint(41.89185, 12.4906)
    val referencePoint = ReferencePoint(GeoPoint(41.89007, 12.49344), VertexPoint(235.84048f, -197.85388f))
    val scale = 1f

    // Act
    val poi3D = poi.toPOI3D(center, referencePoint, scale)

    // Assert
    poi3D.position.x.toInt() `should be equal to` -63
    poi3D.position.z.toInt() `should be equal to` 68
  }

  @Test
  fun `map real world POI position to 3D vertex in 4th quadrant`() {
    // Arrange
    val poi = POI(GeoPoint(41.8933142, 12.4887178))
    val center = GeoPoint(41.89185, 12.4906)
    val referencePoint = ReferencePoint(GeoPoint(41.89007, 12.49344), VertexPoint(235.84048f, -197.85388f))
    val scale = 1f

    // Act
    val poi3D = poi.toPOI3D(center, referencePoint, scale)

    // Assert
    poi3D.position.x.toInt() `should be equal to` -156
    poi3D.position.z.toInt() `should be equal to` -162
  }
}
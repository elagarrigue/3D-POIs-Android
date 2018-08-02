package com.blacktobacco.a3dpoisdata.externalsource

import com.blacktobacco.a3dpoisdomain.maptile.GeoPoint
import com.blacktobacco.a3dpoisdomain.maptile.POI

fun TomTomResult.toPOI(): POI = POI(GeoPoint(position.lat.toDouble(), position.lon.toDouble()), poi.name)
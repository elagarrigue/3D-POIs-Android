package com.blacktobacco.a3dpoisdomain.maptile

import rx.Observable

interface POIsRepository {

  fun getPOI(center:GeoPoint) : Observable<POI>
}
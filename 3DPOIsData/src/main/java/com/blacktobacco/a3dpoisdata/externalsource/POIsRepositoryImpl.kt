package com.blacktobacco.a3dpoisdata.externalsource

import com.blacktobacco.a3dpoisdomain.maptile.GeoPoint
import com.blacktobacco.a3dpoisdomain.maptile.POI
import com.blacktobacco.a3dpoisdomain.maptile.POIsRepository
import rx.Observable

internal class POIsRepositoryImpl(
    private val tomtomSearchApi: TomtomSearchApi
) : POIsRepository {

  private val tomtomKey = "YOUR_TOMTOM_SEARCH_KEY"

  override fun getPOI(center: GeoPoint) : Observable<POI>
  = tomtomSearchApi.searchAsync("coffee",
      tomtomKey,
      center.lat.toFloat(), center.long.toFloat(), 30000)
      .flatMap { Observable.from(it.results) }
      .map {it.toPOI()}
}
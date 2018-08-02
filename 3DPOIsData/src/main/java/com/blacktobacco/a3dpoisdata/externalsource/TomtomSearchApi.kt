package com.blacktobacco.a3dpoisdata.externalsource

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface TomtomSearchApi {

  @GET("search/2/poiSearch/{query}.json")
  fun searchAsync(
      @Path("query") query:String,
      @Query("key") key: String,
      @Query("lat") lat: Float,
      @Query("lon") lon: Float,
      @Query("radius") radius: Int
  ): Observable<TomtomSearchResponse>
}
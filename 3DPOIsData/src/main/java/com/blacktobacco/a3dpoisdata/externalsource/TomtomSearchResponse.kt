package com.blacktobacco.a3dpoisdata.externalsource

data class POI(val name: String)
data class Position(val lat: Float, val lon: Float)
data class TomTomResult(val poi: POI, val position: Position)
class TomtomSearchResponse(val results: Array<TomTomResult>)
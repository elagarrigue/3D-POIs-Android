package com.blacktobacco.a3dpoisdata.externalsource

import com.blacktobacco.a3dpoisdomain.maptile.POIsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

@Module
class POIsDataModule {

  @Provides
  internal fun tomtomSearchApi(): TomtomSearchApi =
      Retrofit.Builder()
          .baseUrl("https://api.tomtom.com/")
          .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(TomtomSearchApi::class.java)


  @Provides
  fun getPOIsRepository(tomtomSearchApi: TomtomSearchApi): POIsRepository =
      POIsRepositoryImpl(tomtomSearchApi)

}
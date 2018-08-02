package com.blacktobacco.a3dpois.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule {

  @Provides
  @Singleton
  internal fun provideContext(application: Application): Context {
    return application
  }
}

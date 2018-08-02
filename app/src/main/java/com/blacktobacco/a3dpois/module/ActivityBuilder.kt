package com.blacktobacco.a3dpois.module

import com.blacktobacco.a3dpois.arscene.ARSceneActivity
import com.blacktobacco.a3dpoisdata.externalsource.POIsDataModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [ARSceneActivityModule::class, POIsDataModule::class])
  internal abstract fun bindARSceneActivity(): ARSceneActivity

}

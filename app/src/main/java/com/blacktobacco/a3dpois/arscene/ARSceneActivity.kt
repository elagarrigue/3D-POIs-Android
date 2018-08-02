package com.blacktobacco.a3dpois.arscene

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.blacktobacco.a3dpois.R
import com.google.ar.sceneform.ux.ArFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class ARSceneActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModel: ARSceneViewModel


  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    if (!checkIsSupportedDeviceOrFinish(this)) {
      return
    }

    setContentView(R.layout.activity_main)

    viewModel.onError
        .subscribe {
          val toast = Toast.makeText(this, it, Toast.LENGTH_LONG)
          toast.setGravity(Gravity.CENTER, 0, 0)
          toast.show()
        }

    viewModel.loadMapData(this)

    val arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment

    arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
      viewModel.onPlaneHit(hitResult, arFragment.arSceneView.scene, arFragment.transformationSystem)
    }
  }

  companion object {
    private val MIN_OPENGL_VERSION = 3.1

    fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show()
        activity.finish()
        return false
      }
      val openGlVersionString = (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
          .deviceConfigurationInfo
          .glEsVersion
      if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
        Toast.makeText(activity, "Sceneform requires OpenGL ES 3.1 or later", Toast.LENGTH_LONG).show()
        activity.finish()
        return false
      }
      return true
    }
  }
}

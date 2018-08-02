package com.blacktobacco.a3dpois.arscene

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import com.blacktobacco.a3dpois.R
import com.google.ar.core.HitResult
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.NodeParent
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem
import com.jakewharton.rxrelay.PublishRelay
import net.javacrumbs.futureconverter.java8rx.FutureConverter.toSingle
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

class ARSceneViewModel @Inject constructor(
    private val mapInfoViewResolver: MapInfoViewResolver
) {

  private var mapRenderable: ModelRenderable? = null
  private val poiRenderables = mutableListOf<POIRenderable>()

  private val onErrorRelay = PublishRelay.create<String>()
  val onError: Observable<String> = onErrorRelay.asObservable()

  fun loadMapData(context: Context) {
    mapInfoViewResolver.getMapInfo()
        .flatMap { mapInfoView ->
          Observable.combineLatest(
              Observable.just(mapInfoView.mapRes)
                  .doOnNext {
                    ModelRenderable.builder()
                        .setSource(context, it)
                        .build()
                        .thenAccept { renderable -> mapRenderable = renderable }
                        .exceptionally { _ ->
                          onErrorRelay.call("Unable to load map renderable")
                          null
                        }
                  },
              mapInfoViewResolver.getPOI3D(mapInfoView))
          { _, poi -> poi }
        }
        .observeOn(AndroidSchedulers.mainThread())
        .flatMapSingle { poi ->
          toSingle(MaterialFactory.makeOpaqueWithColor(context, com.google.ar.sceneform.rendering.Color(Color.RED)))
              .map { ShapeFactory.makeSphere(0.03f, poi.position, it) }
              .zipWith(toSingle(MaterialFactory.makeOpaqueWithColor(context, com.google.ar.sceneform.rendering.Color(Color.DKGRAY)))
                  .map {
                    ShapeFactory.makeCube(Vector3(0.01f, 0.2f, 0.01f),
                        Vector3(poi.position.x, 0.0f, poi.position.z), it)
                  }
              ) { poiPinBall, poiPinStick -> Pair(poiPinBall, poiPinStick) }
              .flatMap { (poiPinBall, poiPinStick) ->
                toSingle(ViewRenderable.builder().setView(context, R.layout.info).build())
                    .map { view ->
                      POIRenderable(poiPinBall, poiPinStick,
                          Triple(view, Vector3(poi.position.x, (2 until 8).random() * 0.05f, poi.position.z), poi.label))
                    }
              }
        }
        .subscribe {
          poiRenderables.add(it)
        }
  }

  fun onPlaneHit(hitResult: HitResult, nodeParent: NodeParent, transformationSystem: TransformationSystem) {

    if (mapRenderable != null) {

      val anchor = hitResult.createAnchor()
      val anchorNode = AnchorNode(anchor)
      anchorNode.setParent(nodeParent)

      val map = TransformableNode(transformationSystem)
      map.setParent(anchorNode)
      map.renderable = mapRenderable
      map.select()

      poiRenderables.forEach {
        val poiPinBall = Node()
        poiPinBall.setParent(map)
        poiPinBall.renderable = it.poiPinBall

        val poiPinStick = Node()
        poiPinStick.setParent(map)
        poiPinStick.renderable = it.poiPinStick

        val info = Node()
        info.localPosition = it.infoView.second
        info.setParent(poiPinBall)
        info.renderable = it.infoView.first

        it.infoView.first.view.findViewById<TextView>(R.id.infoLabel).text = it.infoView.third
      }
    }
  }


}

data class POIRenderable(val poiPinBall: ModelRenderable,
                         val poiPinStick: ModelRenderable,
                         val infoView: Triple<ViewRenderable, Vector3, String>)

fun ClosedRange<Int>.random() =
    Random().nextInt((endInclusive + 1) - start) +  start
package com.example.kotlinsceneform

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.Color
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var arrayView : Array<View>

    lateinit var bearReadable: ModelRenderable
    lateinit var catReadable: ModelRenderable
    lateinit var cowReadable: ModelRenderable
    lateinit var dogReadable: ModelRenderable
    lateinit var elephantReadable: ModelRenderable
    lateinit var ferretReadable: ModelRenderable
    lateinit var hipoReadable: ModelRenderable

    internal var selected = 1 // Default 1 is choose the bear

    lateinit var arFragment: ArFragment

    override fun onClick(view: View?) {

        if (view!!.id == R.id.bear)
        {
            selected = 1
            mySetBackground(view!!.id)
        }
        else if (view!!.id == R.id.cat)
        {
            selected = 2
            mySetBackground(view!!.id)
        }
        else if (view!!.id == R.id.cow)
        {
            selected = 3
            mySetBackground(view!!.id)
        }
        else if (view!!.id == R.id.dog)
        {
            selected = 4
            mySetBackground(view!!.id)
        }
        else if (view!!.id == R.id.elephant)
        {
            selected = 5
            mySetBackground(view!!.id)
        }
        else if (view!!.id == R.id.ferret)
        {
            selected = 6
            mySetBackground(view!!.id)
        }
        else if (view!!.id == R.id.hipo)
        {
            selected = 7
            mySetBackground(view!!.id)
        }
    }

    private fun mySetBackground(id: Int) {

        for (i in arrayView.indices)
        {
            if (arrayView[i].id == id)
            {
                arrayView[i].setBackgroundColor(android.graphics.Color.parseColor("#80333638"))
            }
            else
            {
                arrayView[i].setBackgroundColor(android.graphics.Color.TRANSPARENT)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupArray()
        setupClickListener()
        setup3DModel()

        arFragment = supportFragmentManager.findFragmentById(R.id.scene_form_fragment) as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)

            anchorNode.setParent(arFragment.arSceneView.scene)

            CreateModel(anchorNode, selected)
        }

    }

    private fun CreateModel(anchorNode: AnchorNode, selected: Int) {

        if (selected == 1)
        {
            val bear = TransformableNode(arFragment.transformationSystem)
            bear.setParent(anchorNode)
            bear.renderable = bearReadable
            bear.select()
        }
    }

    private fun setup3DModel() {

        ModelRenderable.builder()
            .setSource(this, R.raw.bear)
            .build()
            .thenAccept { ModelRenderable -> bearReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Bear Model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.cat)
            .build()
            .thenAccept { ModelRenderable -> catReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Cat Model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.cow)
            .build()
            .thenAccept { ModelRenderable -> cowReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Cow Model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.dog)
            .build()
            .thenAccept { ModelRenderable -> dogReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Dog Model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.elephant)
            .build()
            .thenAccept { ModelRenderable -> elephantReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Elephant Model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.ferret)
            .build()
            .thenAccept { ModelRenderable -> ferretReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Ferret Model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder()
            .setSource(this, R.raw.hippopotamus)
            .build()
            .thenAccept { ModelRenderable -> hipoReadable = ModelRenderable }
            .exceptionally { throwable ->
                Toast.makeText(this@MainActivity, "Unnable to load Hippopotamus Model", Toast.LENGTH_SHORT).show()
                null
            }
    }

    private fun setupClickListener() {

        for (i in arrayView.indices)
        {
            arrayView[i].setOnClickListener(this)
        }
    }

    private fun setupArray() {

        arrayView = arrayOf(
            bear,
            cat,
            cow,
            dog,
            elephant,
            ferret,
            hipo
        )
    }
}

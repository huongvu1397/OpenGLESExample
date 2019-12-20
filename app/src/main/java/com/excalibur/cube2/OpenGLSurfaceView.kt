package com.excalibur.cube2

import android.content.Context
import android.opengl.GLSurfaceView
import android.opengl.GLU
import android.util.AttributeSet
import android.view.KeyEvent
import com.excalibur.cube.Cube
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGLSurfaceView(context: Context) :  GLSurfaceView(context),
    GLSurfaceView.Renderer {
    private var mCube: Cube? = null
    private var mXrot = 0f
    private var mYrot = 0f
    private var mXspeed = 0f
    private var mYspeed = 0f

    init {
        requestFocus()
        isFocusableInTouchMode = true
        mCube = Cube()
    }



    override fun onDrawFrame(gl: GL10?) {
        gl?.apply {
            glClear(GL10.GL_COLOR_BUFFER_BIT)
            glLoadIdentity()
            glTranslatef(0f, 0f, -10f)
            glRotatef(mXrot, 1f, 0f, 0f)
            glRotatef(mYrot, 0f, 1f, 0f)
            mCube?.draw(gl)
            glLoadIdentity()
        }
        mXrot += mXspeed
        mYrot += mYspeed
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) mYspeed -= 0.1f
        else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) mYspeed += 0.1f
        else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) mXspeed -= 0.1f
        else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) mXspeed += 0.1f
        return true
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.apply {
            glViewport(0, 0, width, height)
            glMatrixMode(GL10.GL_PROJECTION)
            glLoadIdentity()
            GLU.gluPerspective(gl, 45f, width.toFloat() / height.toFloat(), 0.1f, 100f)
            glViewport(0, 0, width, height)
            glMatrixMode(GL10.GL_MODELVIEW)
            glLoadIdentity()
        }
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl?.apply {
            glClearColor(0f, 0f, 0f, 0.5f)
            glClearDepthf(1f)
            glEnable(GL10.GL_DEPTH_TEST)
            glDepthFunc(GL10.GL_LEQUAL)
            glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        }
    }


}
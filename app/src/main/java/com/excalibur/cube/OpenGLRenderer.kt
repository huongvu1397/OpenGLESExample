package com.excalibur.cube

import android.opengl.GLSurfaceView
import android.opengl.GLU
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGLRenderer : GLSurfaceView.Renderer {
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl?.glClearColor(0f, 0f, 0f, 0.5f)
        gl?.glClearDepthf(1f)
        gl?.glEnable(GL10.GL_DEPTH_TEST)
        gl?.glDepthFunc(GL10.GL_LEQUAL)
        gl?.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
    }


    private var mCube = Cube()
    private var mCubeRotation = 0f

    override fun onDrawFrame(gl: GL10?) {
        gl?.apply {
            glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
            glLoadIdentity()
            glTranslatef(0f, 0f, -10f)
            glRotatef(mCubeRotation, 1f, 1f, 1f)
            mCube.draw(gl)
            glLoadIdentity()
        }
        mCubeRotation -= 0.15f
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl?.apply {
            glViewport(0,0,width,height)
            glMatrixMode(GL10.GL_PROJECTION)
            glLoadIdentity()
            GLU.gluPerspective(gl,45f,width.toFloat()/height.toFloat(),0.1f,100f)
            glViewport(0,0,width,height)
            glMatrixMode(GL10.GL_MODELVIEW)
            glLoadIdentity()
        }
    }


}
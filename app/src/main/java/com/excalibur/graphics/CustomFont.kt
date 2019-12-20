package com.excalibur.graphics

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_custom_font.*

class CustomFont : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_font)
        val t = Typeface.createFromAsset(assets,"")
        PlainTextView.setTypeface(t, Typeface.BOLD)
    }
}

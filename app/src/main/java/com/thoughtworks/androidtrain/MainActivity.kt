package com.thoughtworks.androidtrain

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linear = findViewById<View>(R.id.linear_layout) as LinearLayout

        for(index in 1..10){
            var button_temp=Button(ContextThemeWrapper(this, R.style.scroll_button_group))
            button_temp.setText("button_$index")
            linear.addView(button_temp)
        }

    }
}
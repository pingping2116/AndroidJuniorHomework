package com.thoughtworks.androidtrain

import android.content.Context
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

        fun Context.getStringResourceByName(stringName: String): String? {
            val resId = resources.getIdentifier(stringName, "string", packageName)
            return getString(resId)
        }
        for(index in 1..10){
            var button_temp=Button(ContextThemeWrapper(this, R.style.scroll_button_group))
//            var button_text_name="button_$index"
//            var button_text=R.string.button_text_name
//            button_temp.setText(button_text)
            var button_text_name="button_$index"
            var button_text=getStringResourceByName(button_text_name)
            button_temp.setText(button_text)
            linear.addView(button_temp)
        }

    }
}
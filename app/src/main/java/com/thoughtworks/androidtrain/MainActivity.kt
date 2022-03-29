package com.thoughtworks.androidtrain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        fun goToConstraint(view: View) {
            val intent = Intent(this, ConstraintActivity::class.java)
            startActivity(intent)
        }

        fun goToLogin(view: View) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        var constraint_button=Button(ContextThemeWrapper(this, R.style.scroll_button_group))
        constraint_button.setText("constraint_layout")
        constraint_button.setOnClickListener {
            goToConstraint(constraint_button)
        }
        linear.addView(constraint_button)

        var login_button=Button(ContextThemeWrapper(this, R.style.scroll_button_group))
        login_button.setText("login")
        login_button.setOnClickListener {
            goToLogin(login_button)
        }
        linear.addView(login_button)

        for(index in 3..10){
            var button_temp=Button(ContextThemeWrapper(this, R.style.scroll_button_group))
            var button_text_name="button_$index"
            var button_text=getStringResourceByName(button_text_name)
            button_temp.setText(button_text)
            linear.addView(button_temp)
        }

    }
}
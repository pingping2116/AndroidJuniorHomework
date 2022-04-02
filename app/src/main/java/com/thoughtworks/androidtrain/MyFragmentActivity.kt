package com.thoughtworks.androidtrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button

class MyFragmentActivity : AppCompatActivity() {

    fun showAndroidFragment(){
        val fragmentManager=supportFragmentManager
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_parent_layout,AndroidFragment())
        transaction.commit()
    }

    fun showJavaFragment(){
        val fragmentManager=supportFragmentManager
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_parent_layout,JavaFragment())
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)

        val android_button:Button=findViewById(R.id.android)
        android_button.setOnClickListener (){
            showAndroidFragment()
        }

        val java_button:Button=findViewById(R.id.java)
        java_button.setOnClickListener (){
            showJavaFragment()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.android_menu_item -> {
                showAndroidFragment()
                true
            }
            R.id.java_menu_item -> {
                showJavaFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}

package com.thoughtworks.androidtrain

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private  fun showDialog(title:String,message:String) {
        val alertDialog: AlertDialog
        alertDialog = AlertDialog.Builder(this).create()
        alertDialog.apply {
            setTitle(title)
            setMessage(message)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setButton(
                AlertDialog.BUTTON_POSITIVE, getString(R.string.ok)
            ) { dialog, id -> }
        }
        alertDialog.show()
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),100);
                }

                val contactUri = result.data?.data
                val projection: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

                if (contactUri != null) {
                    contentResolver.query(contactUri, projection, null, null, null).use { cursor ->

                        if (cursor != null && cursor.count>0) {
                            cursor.moveToFirst()
                            val numberIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            val nameIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                            val number = if(numberIndex>=0) cursor.getString(numberIndex) else "null"
                            val name =if(nameIndex>=0) cursor.getString(nameIndex) else "null"
                            cursor.close()

                            showDialog("$name","$number")

                        }
                    }
                }

            }
        }

        private fun selectContact() {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }
            try {
                startForResult.launch(intent)

            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        }

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

            fun goToFragment(view: View) {
                val intent = Intent(this, MyFragmentActivity::class.java)
                startActivity(intent)
            }

            var constraint_button = Button(ContextThemeWrapper(this, R.style.scroll_button_group))
            constraint_button.setText("constraint_layout")
            constraint_button.setOnClickListener {
                goToConstraint(constraint_button)
            }
            linear.addView(constraint_button)

            var login_button = Button(ContextThemeWrapper(this, R.style.scroll_button_group))
            login_button.setText("login")
            login_button.setOnClickListener {
                goToLogin(login_button)
            }
            linear.addView(login_button)

            var pick_contact_button = Button(ContextThemeWrapper(this, R.style.scroll_button_group))
            pick_contact_button.setText("pick_contact")
            pick_contact_button.setOnClickListener {
                selectContact()
            }
            linear.addView(pick_contact_button)

            var fragment_button = Button(ContextThemeWrapper(this, R.style.scroll_button_group))
            fragment_button.setText("fragment")
            fragment_button.setOnClickListener {
                goToFragment(fragment_button)
            }
            linear.addView(fragment_button)

            for (index in 5..10) {
                var button_temp = Button(ContextThemeWrapper(this, R.style.scroll_button_group))
                var button_text_name = "button_$index"
                var button_text = getStringResourceByName(button_text_name)
                button_temp.setText(button_text)
                linear.addView(button_temp)
            }

        }
}
package com.example.sqlliteappc10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class SharenPreferenceApp : AppCompatActivity() {

    lateinit var etnNombre: EditText
    lateinit var etnApellido :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharen_preference_app)



    }

   fun guardar()
   {
       var pref = getSharedPreferences("datospersona", Context.MODE_PRIVATE)
       var editor = pref.edit()

       editor.putString("nombre",etnNombre.text.toString())
       editor.putString("apellido", etnApellido.toString())

   }


}
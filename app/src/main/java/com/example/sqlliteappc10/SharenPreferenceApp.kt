package com.example.sqlliteappc10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class SharenPreferenceApp : AppCompatActivity() {

    lateinit var etnNombre: EditText
    lateinit var etnApellido :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharen_preference_app)

        etnNombre = findViewById(R.id.etnNombre)
        etnApellido = findViewById(R.id.etnApellido)

    }

   fun guardar(v:View)
   {
       var pref = getSharedPreferences("datospersona", Context.MODE_PRIVATE)
       var editor = pref.edit()

       editor.putString("nombre",etnNombre.text.toString())
       editor.putString("apellido", etnApellido.toString())
       editor.commit()
       Toast.makeText(this, "se a guardado exitosamente",Toast.LENGTH_LONG).show()
   }


}
package com.example.sqlliteappc10

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var edit_Codigo:EditText
    private lateinit var edit_Descripcion: EditText
    private lateinit var edit_precio: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_Codigo = findViewById(R.id.edit_Codigo)
        edit_Descripcion = findViewById(R.id.edit_Descripcion)
        edit_precio = findViewById(R.id.edit_precio)
    }
    //FUNCION PARA REGISTRAR PRODUCTOS
    fun registrar(V: View)
    {//Conexion a la base de datos  // aqui van los valors creados en la base de datos
        val admin = AdminSQLlite(this,"TIENDA",null,1)
        val BaseDatos:SQLiteDatabase=admin.writableDatabase //este ultimo ayuda a leer y escribir en nuestra base de datos

        //
        val codigo = edit_Codigo.text.toString()
        val descripcion = edit_Descripcion.text.toString()
        val precio = edit_precio.text.toString()
        //validacion para que ningun campo quede vacio
        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty())
        {
            val registro = ContentValues()
            registro.put("codigo", codigo)
            registro.put("descripcion",descripcion)
            registro.put("precio",precio)

            BaseDatos.insert("articulos", null, registro)
            BaseDatos.close()

            edit_Codigo.setText("")
            edit_Descripcion.setText("")
            edit_precio.setText("")

            Toast.makeText(this, "Registro Exitoso",Toast.LENGTH_LONG).show()

        }else
        {//Esto es por si el usuario no llena uno o todos los campos
            Toast.makeText(this,"No sea imbecil llene los campos xd", Toast.LENGTH_SHORT).show()
        }


    }

}
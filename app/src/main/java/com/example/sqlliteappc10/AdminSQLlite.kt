package com.example.sqlliteappc10

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.prefs.PreferencesFactory

//Aqui es donde heredaremos de la clase principal para llenar la base de datos
//Agregamos los parametros que tendra nuestra base de datos y su tipo
class AdminSQLlite
    (Contex: Context?, name:String?,factory:SQLiteDatabase.CursorFactory?,version:Int):SQLiteOpenHelper(Contex,name,factory, version)  {
    //metodos implementados en base al AdminSQLlite

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        //con esto llamamos una sentencia sql           entre parentesis seran los campos de la tabla
        baseDeDatos?.execSQL("create table articulos(codigo int primary key,descripcion text, precio real)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}
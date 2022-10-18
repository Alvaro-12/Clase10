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
    //FUNCION PARA REGISTRAR PRODUCTOS EN EL BOTON
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

//Codigo para el boton de buscar el cual busca un producto por medio del codigo de este que este registrado
    fun buscar(Vista:View)
    //primeramente despues de crear la funcion va la conexion a la base de datos
    { val admin = AdminSQLlite(this,"TIENDA",null,1)
        val BaseDatos:SQLiteDatabase=admin.writableDatabase //este ultimo ayuda a leer y escribir en nuestra base de datos

        val codigo = edit_Codigo.text.toString()


        //si codigo no esta vacio que haga lo siguiente:
        if(!codigo.isEmpty())
        {//variable fila es igual a llamar base de datos y mandar en sql la seleccion de la informacion de codigo y si codigo existe
            val fila = BaseDatos.rawQuery("select descripcion, precio from articulos where codigo = $codigo", null)

            if(fila.moveToFirst())
            {edit_Descripcion.setText(fila.getString(0))
                edit_precio.setText(fila.getString(1))
                BaseDatos.close()
            }else
            {
                Toast.makeText(this,"No existe el articul", Toast.LENGTH_LONG).show()
                BaseDatos.close()
            }
        }else
        {
            Toast.makeText(this,"Debes ingresar el codigo", Toast.LENGTH_LONG).show()
        }
    }

    //Funfion para modificar
    fun Modificaar(V:View)
    //conectar base de datos
    {val admin = AdminSQLlite(this,"TIENDA",null,1)
        val BaseDatos:SQLiteDatabase=admin.writableDatabase //este ultimo ayuda a leer y escribir en nuestra base de datos

        //llamar variables a utilizar
        val codigo = edit_Codigo.text.toString()
        val descripcion = edit_Descripcion.text.toString()
        val precio = edit_precio.text.toString()
//validar si las variables se encuentran vacias
        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty())
        {//llamamos las variables de la base y las que utilizaremos
            val registro = ContentValues()
                //registro.put("codigo",codigo)
                registro.put("descripcion",descripcion)
                registro.put("precio", precio)

            val cantidad:Int = BaseDatos.update("articulos",registro,"codigo=$codigo",null)
           //CERRAMOS LA BASE DE DATOS ESTO SE HACE SIEMPRE ALFINAL DE UNA FUNCION LA CUAL TENGA ACCESO A LA BASE DE DATOS PORM MEDIO DE LA CONEXION
            BaseDatos.close()
//si la actualizacion se realiza correctamente
            if(cantidad==1)
            {
                Toast.makeText(this,"Articulo modificado correctamente", Toast.LENGTH_LONG).show()
                BaseDatos.close()
            //si la actualizacion no se realiza correctamente
                //este else funciona si ingresa un codigo de articulo que no este registrado
            }else{Toast.makeText(this,"El articulo no existe", Toast.LENGTH_LONG).show()}
            BaseDatos.close()
//Cerrar la base de datos siempre en una funcion en caso que se cumpla como en el if/else ya que si se cumple y no cierra no funcionara


            edit_precio.setText("")
            edit_Descripcion.setText("")
            edit_Codigo.setText("")

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_LONG).show()
        }else
        {Toast.makeText(this,"Rellene todos los campos", Toast.LENGTH_LONG).show()}
    }

    fun eliminar(v:View)
    {val admin = AdminSQLlite(this,"TIENDA",null,1)
        val BaseDatos:SQLiteDatabase=admin.writableDatabase //este ultimo ayuda a leer y escribir en nuestra base de datos

        val codigo = edit_Codigo.text.toString()

        if(!codigo.isEmpty())
        {val canitdad:Int = BaseDatos.delete("aticulos", "codigo=${codigo}", null)
            BaseDatos.close()
            edit_Codigo.setText("")
            edit_Descripcion.setText("")
            edit_precio.setText("")

            if(canitdad==1)
            {
                Toast.makeText(this,"Articulo Eliminado", Toast.LENGTH_LONG).show()
                BaseDatos.close()
            }else{
                Toast.makeText(this,"No existe el articulo", Toast.LENGTH_LONG).show()
                BaseDatos.close()
            }

        }else{Toast.makeText(this,"Debes ingresar un codigo", Toast.LENGTH_LONG).show()}



    }

}
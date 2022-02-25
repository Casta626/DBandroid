package com.example.db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.db.Productos.Producto
import com.example.db.db.ProductoDatabase
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)

        var idProducto: Int? = null

        if (intent.hasExtra("producto")){
            val producto = intent.extras?.getSerializable("producto") as Producto

            eT_nombre.setText(producto.nombre)
            eT_precio.setText(producto.precio.toString())
            eT_descripcion.setText(producto.descripcion)
            idProducto = producto.idProducto
        }

        val database = ProductoDatabase.getDatabase(this)

        boton_guargar.setOnClickListener {
            val nombre = eT_nombre.text.toString()
            val precio = eT_precio.text.toString().toDouble()
            val descripcion = eT_descripcion.text.toString()

            val producto = Producto(nombre, precio, descripcion, R.drawable.procesador)

            if (idProducto!= null) {
                CoroutineScope(Dispatchers.IO).launch {
                    producto.idProducto = idProducto

                    database.productos().update(producto)

                    this@NuevoProductoActivity.finish()
                }
            }else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().insertAll(producto)

                    this@NuevoProductoActivity.finish()
                }
            }
        }
    }
}
package com.example.db.Productos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.db.AyudaActivityDB
import com.example.db.NuevoProductoActivity
import com.example.db.R
import com.example.db.db.ProductoDatabase
import kotlinx.android.synthetic.main.activity_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoActivity : AppCompatActivity() {

    private lateinit var database: ProductoDatabase
    private lateinit var  producto: Producto
    private lateinit var productoLiveData: LiveData<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        database = ProductoDatabase.getDatabase(this)

        val idProducto = intent.getIntExtra("id",0)

        productoLiveData = database.productos().get(idProducto)

        productoLiveData.observe(this, Observer {
            producto = it

            nombre_producto.text = producto.nombre
            precio_producto.text = "${producto.precio}"
            descripcion_producto.text = producto.descripcion
            imagen.setImageResource(producto.imagen)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_editar -> {
                val intent = Intent(this, NuevoProductoActivity::class.java)
                intent.putExtra("producto", producto)
                startActivity(intent)
            }

            R.id.item_borrar -> {
                productoLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.productos().delete(producto)
                    this@ProductoActivity.finish()
                }
            }
            R.id.item_ayuda -> {
                val intent2 = Intent(this, AyudaActivityDB::class.java)
                startActivity(intent2)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
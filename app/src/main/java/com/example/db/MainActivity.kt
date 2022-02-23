package com.example.db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val producto_caja = Producto("Caja Extended ATX",103.99,"Caja Extended ATX RGB para ver tu PC con una sonrisa",R.drawable.caja)
        val producto_FA = Producto("FA 850w",123.99,"Fuente de alimentación de 850w para un funcionamiento máximo",R.drawable.fuente_alimentacion)
        val producto_procesador = Producto("AMD Ryzen 5 3600GHz",179.99,"Procesador confiable para una tarjeta gráfica potente",R.drawable.procesador)
        val producto_grafica = Producto("NVIDIA RTX 3090 24GB",999.99,"La tarjeta gráfica mas potente",R.drawable.grafica)

        val listaProductos = listOf(producto_FA,producto_caja,producto_grafica,producto_procesador)

        val adapter = ProductosAdapter(this, listaProductos)

        lista.adapter = adapter

        lista.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("producto", listaProductos[position])
            startActivity(intent)
        }
    }
}
package com.example.db.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.db.Productos.Producto

@Dao
interface  ProductosDao{
    @Query("SELECT * FROM productos")
    fun getAll(): LiveData<List<Producto>>

    @Query("SELECT * FROM productos WHERE idProducto = :id")
    fun get (id: Int) : LiveData<Producto>

    @Insert
    fun insertAll(vararg producto: Producto)

    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)

}
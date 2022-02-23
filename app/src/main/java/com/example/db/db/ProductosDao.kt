package com.example.db.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

}
package com.example.db.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.db.Productos.Producto

@Database(entities = [Producto::class], version = 1)
abstract class ProductoDatabase: RoomDatabase() {

    abstract fun productos(): ProductosDao

    companion object{
        @Volatile
        private var INSTANCE: ProductoDatabase? = null

        fun getDatabase(context: Context): ProductoDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductoDatabase::class.java,
                    "producto_database"
                ).build()

                INSTANCE =instance

                return instance
            }
        }
    }
}
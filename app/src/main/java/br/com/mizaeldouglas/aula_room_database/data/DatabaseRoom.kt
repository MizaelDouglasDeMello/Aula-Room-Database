package br.com.mizaeldouglas.aula_room_database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.mizaeldouglas.aula_room_database.data.dao.IUserDAO
import br.com.mizaeldouglas.aula_room_database.data.model.User

@Database(entities = [User::class], version = 1)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun getUserDao(): IUserDAO

    companion object {
        private const val DATABASE_NAME = "user_db"

        fun getIsntance(context: Context): DatabaseRoom {
            return Room.databaseBuilder(
                context,
                DatabaseRoom::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
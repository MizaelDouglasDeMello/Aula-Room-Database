package br.com.mizaeldouglas.aula_room_database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.mizaeldouglas.aula_room_database.data.model.User

@Dao
interface IUserDAO {

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user ORDER BY name ASC")
    fun listUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): User

}
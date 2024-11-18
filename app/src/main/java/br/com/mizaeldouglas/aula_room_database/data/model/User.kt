package br.com.mizaeldouglas.aula_room_database.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
//    val email: String,
//    val password: String,
//    val phone: String,
//    val age: Int

    // @Ignore val imc: Double
)
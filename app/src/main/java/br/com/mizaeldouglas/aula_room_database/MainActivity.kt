package br.com.mizaeldouglas.aula_room_database

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.mizaeldouglas.aula_room_database.data.DatabaseRoom
import br.com.mizaeldouglas.aula_room_database.data.dao.IUserDAO
import br.com.mizaeldouglas.aula_room_database.data.model.User
import br.com.mizaeldouglas.aula_room_database.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var database: DatabaseRoom
    private lateinit var userDao: IUserDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = DatabaseRoom.getIsntance(this)
        userDao = database.getUserDao()

        binding.btnSalvar.setOnClickListener {
            val name = binding.editName.text.toString()

            val user = User(
                id = 0,
                name
            )
            CoroutineScope(Dispatchers.IO).launch {
                userDao.insertUser(user)
            }
        }


        binding.btnDeletar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val user = userDao.getUser(1)
                userDao.deleteUser(user)
            }


        }


        binding.btnAtualizar.setOnClickListener {

            val name = binding.editName.text.toString()

            val user = User(
                id = 2,
                name
            )
            CoroutineScope(Dispatchers.IO).launch {
                userDao.updateUser(user)
            }

        }


        binding.btnListar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val users = userDao.listUsers()
                Log.i("List_Users", "onCreate: $users")

                var lista = ""
                users.forEach {
                    lista += "${it.id})  ${it.name} \n"
                }
                withContext(Dispatchers.Main) {
                    binding.txtListaUsr.text = lista
                }
            }
        }
    }
}
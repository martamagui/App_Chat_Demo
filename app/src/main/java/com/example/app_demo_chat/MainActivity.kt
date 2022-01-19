package com.example.app_demo_chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.app_demo_chat.databinding.ActivityMainBinding
import com.example.app_demo_chat.provider.db.ChatDB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var db :ChatDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        val navView: BottomNavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//        val db = Room.databaseBuilder(applicationContext, ChatDB::class.java, "chat.db")
//            .allowMainThreadQueries()
//            .build()
//        db.messageDao().findAll()
//        val message = MessageEntity(0,"Mensaje de BD","date",1)
//        db.messageDao().createMessage(message)
        db = Room.databaseBuilder(applicationContext, ChatDB::class.java, "chat.db")
            .allowMainThreadQueries()
            .build()
    }
}
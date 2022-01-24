package com.example.app_demo_chat

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.app_demo_chat.databinding.ActivityMainBinding
import com.example.app_demo_chat.provider.db.ChatDB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var db: ChatDB
    val CHANNEL_ID = "Canal1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        db = Room.databaseBuilder(applicationContext, ChatDB::class.java, "chat.db")
            .allowMainThreadQueries()
            .build()
        //Notificación
        val intent = Intent(this, MainActivity::class.java)
        //Flags, dice que se abra una neuva actividad como tarea nuva(refresca la pila de acticvidades)
        // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Titulo de la notifiación")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Otras bibliotecas del grupo com.android.support también incluyen support-compat como dependencia transitiva. Por lo tanto, si ya usas otras API de biblioteca de compatibilidad, es posible que tengas acceso a NotificationCompat sin la necesidad de tener la dependencia exacta que se muestra arriba."))
            .setContentText("Texto de la notificación")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val channelName = "Mensajes directos"
        val channelName2 = "Mensajes comerciales"
        val channelDescription = "Recibirás todos los mensajes directos por esta vía"
        val channelDescription2 = "Recibirás toda la publi y descuentos"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
                description = channelDescription
            }
            val channel2 = NotificationChannel("Canal2", channelName2, importance).apply {
                description = channelDescription2
            }
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(channel2)
        }
        notificationManager.notify(1, notification)

    }
}
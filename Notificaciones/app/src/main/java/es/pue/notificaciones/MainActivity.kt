package es.pue.notificaciones

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var notiMgr: NotificationManager

    companion object {
        const val CHANNEL_ID = "es.pue.notificaciones.novedades"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notiMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            CHANNEL_ID,
            "Novedades",
            "Novedades Gadgets"
        )

        sendNotification()
    }

    private fun createNotificationChannel(id: String, name: String, description: String) {

        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)

        with(channel, {
            this.description = description
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
        })

        notiMgr.createNotificationChannel(channel)
    }

    private fun sendNotification() {
        val notificationId = 100
        val intent = Intent(this, NotificationsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val icon = Icon.createWithResource(this, android.R.drawable.ic_media_play)
        val openAction = Notification.Action.Builder(icon, "Abrir", pendingIntent).build()
        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Actualización Jetpack")
            .setContentText("Android ya tiene disponible la nueva versión de Jetpack")
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setChannelId(CHANNEL_ID)
            .setActions(openAction)
            .setContentIntent(pendingIntent)
            .build()

        notiMgr.notify(notificationId, notification)
    }
}

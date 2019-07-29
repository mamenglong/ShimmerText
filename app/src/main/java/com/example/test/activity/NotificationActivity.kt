package com.example.test.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.receiver.NotificationActionReceiver
import android.app.Notification
import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.core.app.TaskStackBuilder
import com.example.test.R


class NotificationActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        NotificationActionReceiver.showNotification(this, false)
        val ii=Intent(NotificationActionReceiver.ACTION_NOTIFICATION_RECEIVER)
        ii.addFlags(0x01000000)
        sendBroadcast(ii)
        val intent=Intent()
        intent.action="br"
        intent.addFlags(0x01000000)
        sendBroadcast(intent)
//        notification()
    }
    private fun notification() {
        val intent = Intent(this, LauncherActivity::class.java)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //8.0 默认通知channelId default
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "default"
            val channelName = "通知"
            manager.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }

        //??TaskStackBuilder
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(LauncherActivity::class.java)
        stackBuilder.addNextIntent(intent)

        val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("通知")
            .setContentTitle("这是个通知")
            .setContentText("通知")
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(1, notification)
    }


}

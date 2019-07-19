package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.receiver.NotificationActionReceiver

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        NotificationActionReceiver.showNotification(this, false)
        sendBroadcast(Intent(NotificationActionReceiver.EXTRA_NOTIFICATION_ACTION))
    }
}

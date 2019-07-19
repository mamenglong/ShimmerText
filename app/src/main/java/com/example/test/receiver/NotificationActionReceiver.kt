package com.example.test.receiver

import android.app.ActivityManager
import android.app.LauncherActivity
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.test.NotificationActivity
import com.example.test.R
import com.example.test.service.FloatWindowService

class NotificationActionReceiver : BroadcastReceiver() {
    val TAG=NotificationActionReceiver::class.java.simpleName

    companion object {
        val NOTIFICATION_ID = 1
        val ACTION_NOTIFICATION_RECEIVER = "com.example.test.receiver.ACTION_NOTIFICATION_RECEIVER"
        val ACTION_PAUSE = 0
        val ACTION_RESUME = 1
        val ACTION_STOP = 2
        val EXTRA_NOTIFICATION_ACTION = "command"

        fun showNotification(context: Context, isPaused: Boolean) {
            val pIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, NotificationActivity::class.java),
                0
            )
            val builder = NotificationCompat.Builder(context)
                .setContentTitle(
                    context.getString(
                        R.string.is_running,
                        context.getString(R.string.app_name)
                    )
                )
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(context.getString(R.string.touch_to_open))
                .setColor(-0x1dea20)
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                .setOngoing(!isPaused)
            if (isPaused) {
                builder.addAction(
                    R.drawable.ic_noti_action_resume, context.getString(R.string.noti_action_resume),
                    getPendingIntent(context, ACTION_RESUME)
                )
            } else {
                builder.addAction(
                    R.drawable.ic_noti_action_pause,
                    context.getString(R.string.noti_action_pause),
                    getPendingIntent(context, ACTION_PAUSE)
                )
            }

            builder.addAction(
                R.drawable.ic_noti_action_stop,
                context.getString(R.string.noti_action_stop),
                getPendingIntent(context, ACTION_STOP)
            )
                .setContentIntent(pIntent)

            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.notify(NOTIFICATION_ID, builder.build())

        }

        fun getPendingIntent(context: Context, command: Int): PendingIntent {
            val intent = Intent(ACTION_NOTIFICATION_RECEIVER)
            intent.putExtra(EXTRA_NOTIFICATION_ACTION, command)
            return PendingIntent.getBroadcast(context, command, intent, 0)
        }

        fun cancelNotification(context: Context) {
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.cancel(NOTIFICATION_ID)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG,"onReceive")
        when (intent.getIntExtra(EXTRA_NOTIFICATION_ACTION, -1)) {
            ACTION_RESUME -> {
                showNotification(context, false)
//                SPHelper.setIsShowWindow(context, true)
                val lollipop = Build.VERSION.SDK_INT >= 21
                if (!lollipop) {
                    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                    val rtis = am.getRunningTasks(1)
                    val act = (rtis[0].topActivity!!.getPackageName() + "\n"
                            + rtis[0].topActivity!!.getClassName())
                    FloatWindowService.setText(act)
                } else {
                    FloatWindowService.setText("")
                }
            }
            ACTION_PAUSE -> {
                showNotification(context, true)
//                FloatWindowService.
//                SPHelper.setIsShowWindow(context, false)
            }
            ACTION_STOP -> {
//                TasksWindow.dismiss(context)
//                SPHelper.setIsShowWindow(context, false)
                cancelNotification(context)
            }
        }
//        context.sendBroadcast(Intent(QuickSettingTileService.ACTION_UPDATE_TITLE))
    }

}
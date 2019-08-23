package com.example.test.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * Author: Menglong Ma
 * Email: mml2015@126.com
 * Date: 19-8-14 下午4:36
 * Description: This is Stas
 * Package: com.example.test.service
 * Project: Test
 */
public class ForegroundService extends Service {
    private static final int SERVICE_ID = 1;

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18) {
            //安卓4.0以下可以直接构建一个空的通知。
            //设置成前台服务，并且去除通知栏消息
            startForeground(SERVICE_ID,new Notification());
        } else if (Build.VERSION.SDK_INT < 26) {
            //设置成前台服务
            startForeground(SERVICE_ID,new Notification());
            //并且去除通知栏消息
            startService(new Intent(this,InnerService.class));
        } else {
            //安卓8.0以上，通知需要设置channel
            //还需要设置通知的重要级别
            //安卓9.0以上，通知会直接显示出来。
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager!=null){
                NotificationChannel channel = new NotificationChannel("long","meng",NotificationManager.IMPORTANCE_NONE);
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this,"long").build();
                startForeground(SERVICE_ID,notification);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private class InnerService extends Service{
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID,new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}

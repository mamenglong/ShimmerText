package com.example.test.activity

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.app.PendingIntent
import android.content.Intent
import com.example.test.R
import com.example.test.service.TimerWidgetService
import android.content.ComponentName
import android.text.format.DateUtils
import android.util.Log


/**
 * Implementation of App Widget functionality.
 */
class DesktopWidget : AppWidgetProvider() {
    final val TAG=DesktopWidget::class.java.simpleName
    //当Widget被添加或者被更新时会调用该方法。上边我们提到通过配置updatePeriodMillis可以定期更新Widget。但是当我们在widget的配置文件中声明了android:configure的时候，添加Widget时则不会调用onUpdate方法。
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        Log.i(TAG,"onUpdate")
        super.onUpdate(context, appWidgetManager, appWidgetIds)
//         There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
    
    //第1次创建时调用，之后再创建不会调用
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }
    //当最后一个部件实例 被删除时 调用  用于清除onEnabled执行的操作
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    
    //部件从host中删除
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        Log.i(TAG,"onDeleted")
        super.onDeleted(context, appWidgetIds)
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        /*
           * * 接收 <action android:name="com.example.test.app_widget_update_time"/>
           * 在其他组件或activity或service中发送这些广播
            */
        Log.i(TAG,"onReceive $context  $intent")
        if (intent?.action.equals("com.example.test.app_widget_update_time")) {
            val time = intent?.getLongExtra("time", 0)
            context?.let {
                time?.let { it1 -> updateWidget(it, it1) }
            }
        }
        val intent= Intent("com.example.test.app_widget_update_time").addFlags(0x01000000).putExtra("time", System.currentTimeMillis())

        context?.sendBroadcast(intent)
    }

    private fun updateWidget(context: Context, time: Long) {
        Log.i(TAG,"updateWidget")
        //RemoteViews处理异进程中的View
        val intent = Intent(context, TimerWidgetService::class.java)
        val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

        val rv = RemoteViews(context.packageName, R.layout.desktop_widget)
        rv.setOnClickPendingIntent(R.id.start_stop, pendingIntent)
        println("time=$time")
        rv.setTextViewText(R.id.appwidget_text, DateUtils.formatElapsedTime(time / 1000))

        val am = AppWidgetManager.getInstance(context)
        val appWidgetIds = am.getAppWidgetIds(ComponentName(context, DesktopWidget::class.java))
        am.updateAppWidget(appWidgetIds, rv)//更新 所有实例
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            Log.i("DesktopWidget","updateAppWidget")
            val widgetText = context.getString(R.string.appwidget_text) + System.currentTimeMillis()
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.desktop_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)
            val intent = Intent(context, TimerWidgetService::class.java)
            val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

            views.setOnClickPendingIntent(R.id.start_stop, pendingIntent)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}


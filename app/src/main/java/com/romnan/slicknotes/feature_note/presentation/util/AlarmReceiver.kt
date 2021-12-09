package com.romnan.slicknotes.feature_note.presentation.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.romnan.slicknotes.R
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private const val ID_NOTE_REMINDER = 100

        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MESSAGE = "extra_message"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra(EXTRA_TITLE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        title?.let {
            message?.let {
                showReminderNotification(context, title, message, ID_NOTE_REMINDER)
            }
        }
    }

    fun setOneTimeReminder(context: Context, dateTime: Calendar, title: String, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_TITLE, title)
        intent.putExtra(EXTRA_MESSAGE, message)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_NOTE_REMINDER, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, dateTime.timeInMillis, pendingIntent)

        Log.e("ONE TIME", "$dateTime")
    }

    private fun showReminderNotification(
        context: Context,
        title: String,
        message: String,
        notifId: Int
    ) {
        val channelId = "slick_notes_1"
        val channelName = "Note Reminder"

        val notifMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(sound)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(channelId)
            notifMgr.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notifMgr.notify(notifId, notification)
    }
}
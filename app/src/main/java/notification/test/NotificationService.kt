package notification.test

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationService : NotificationListenerService() {

    companion object {
        private var instance: NotificationService? = null
        internal val notifications: Array<StatusBarNotification>?
            get() = instance?.activeNotifications
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}
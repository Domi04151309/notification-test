package notification.test

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

        val items = ArrayList<RecyclerViewItem>((NotificationService.notifications?.size ?: 0) + 1)

        // Add item for setting up the notification listener
        items.add(
            RecyclerViewItem(
                ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground),
                resources.getString(R.string.listener_title),
                resources.getString(R.string.listener_summary),
                ""
            )
        )

        // Add the notification items
        run {
            for (item in NotificationService.notifications ?: return@run) {
                var icon: Drawable? = null
                var status = resources.getString(R.string.ok)

                // Try loading the icon
                try {
                    icon = item.notification.smallIcon.loadDrawable(this@MainActivity)
                } catch (e: PackageManager.NameNotFoundException) {
                    // This does not get called even if the Exception is thrown in the Icon class
                    Log.e(resources.getString(R.string.app_name), e.stackTraceToString())
                    status = e.stackTraceToString()
                }

                // Detect if the icon has been loaded correctly and show "Error" if not message has been saved
                if (status == resources.getString(R.string.ok) && icon == null) {
                    status = resources.getString(R.string.error)
                }

                // Add the item to the list
                items.add(
                    RecyclerViewItem(
                        icon,
                        item.packageName,
                        typeToString(item.notification.smallIcon.type),
                        status
                    )
                )
            }
        }

        // Show the items
        recyclerView.adapter = Adapter(items)
    }

    private fun typeToString(type: Int): String {
        return when (type) {
            Icon.TYPE_ADAPTIVE_BITMAP -> "TYPE_ADAPTIVE_BITMAP"
            Icon.TYPE_BITMAP -> "TYPE_BITMAP"
            Icon.TYPE_DATA -> "TYPE_DATA"
            Icon.TYPE_RESOURCE -> "TYPE_RESOURCE"
            Icon.TYPE_URI -> "TYPE_URI"
            Icon.TYPE_URI_ADAPTIVE_BITMAP -> "TYPE_URI_ADAPTIVE_BITMAP"
            else -> "Unknown"
        }
    }
}
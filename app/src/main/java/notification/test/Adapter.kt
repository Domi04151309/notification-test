package notification.test

import android.content.ComponentName
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val items: ArrayList<RecyclerViewItem>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.view.setOnClickListener {
                holder.view.context.startActivity(Intent().apply {
                    component = ComponentName(
                        "com.android.settings",
                        "com.android.settings.Settings\$NotificationAccessSettingsActivity"
                    )
                })
            }
        }

        if (items[position].icon != null) {
            holder.view.findViewById<ImageView>(R.id.icon).setImageDrawable(items[position].icon)
        }
        holder.view.findViewById<TextView>(R.id.packageName).text = items[position].packageName
        holder.view.findViewById<TextView>(R.id.info).text = items[position].info
        holder.view.findViewById<TextView>(R.id.status).text = items[position].status
    }

    override fun getItemCount(): Int = items.size
}
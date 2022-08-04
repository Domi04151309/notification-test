package notification.test

import android.graphics.drawable.Drawable

data class RecyclerViewItem(
    val icon: Drawable?,
    val packageName: String,
    val info: String,
    val status: String
)
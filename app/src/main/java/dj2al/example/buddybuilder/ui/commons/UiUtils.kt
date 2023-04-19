package dj2al.example.buddybuilder.ui.commons

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import dj2al.example.buddybuilder.R

fun <A : Activity> Context.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}

fun String?.toStringOrEmpty(): String {
    return this ?: ""
}

fun String?.toDrawableInt(): Int {
    for (i in R.drawable::class.java.fields) {
        if (i.name == this) {
            return i.getInt(null)
        }
    }
    return R.drawable.ic_not_found
}
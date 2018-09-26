package momsday.careworker.util

import android.content.Context
import android.content.SharedPreferences

private fun getPref(context: Context): SharedPreferences {
    val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    return pref
}

fun saveToken(context: Context, token: String, isAccess: Boolean = true) {
    val editor = getPref(context).edit()
    editor.putString(getKey(isAccess), token)
    editor.apply()
}

fun removeToken(context: Context, isAccess: Boolean = true) {
    val editor = getPref(context).edit()
    editor.remove(getKey(isAccess))
    editor.apply()
}

fun getToken(context: Context, isAccess: Boolean = true): String {
    return "JWT " + getPref(context).getString(getKey(isAccess), "")
}

fun saveId(context: Context, id: String) {
    val editor = getPref(context).edit()
    editor.putString("id", id)
    editor.apply()
}

fun getId(context: Context): String {
    return getPref(context).getString("id", "")
}

private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"
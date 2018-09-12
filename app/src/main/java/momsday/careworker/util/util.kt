package momsday.careworker.util

import android.view.View
import android.widget.TextView

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun TextView.toText() = this.text.toString()

package com.denysnovoa.nzbmanager.common.framework.ui

import android.os.Build
import android.support.annotation.StyleRes
import android.support.v4.widget.TextViewCompat
import android.text.Html
import android.widget.TextView

fun TextView.setTextAppearanceCompatible(@StyleRes textAppearance: Int)
        = TextViewCompat.setTextAppearance(this, textAppearance)


fun TextView.setTextFormHtmlCompatible(source: String) {
    if (Build.VERSION.SDK_INT >= 24) {
        this.text = Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
        this.text = Html.fromHtml(source)
    }
}

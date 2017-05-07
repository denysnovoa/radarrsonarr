package com.denysnovoa.nzbmanager.common.framework.ui

import android.support.annotation.StyleRes
import android.support.v4.widget.TextViewCompat
import android.widget.TextView

fun TextView.setTextAppearanceCompatible(@StyleRes textAppearance: Int)
        = TextViewCompat.setTextAppearance(this, textAppearance)

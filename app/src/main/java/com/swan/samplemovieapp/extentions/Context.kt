package com.swan.samplemovieapp.extentions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes stringResId: Int) =
    Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show()
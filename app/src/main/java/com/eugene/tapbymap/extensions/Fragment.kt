package com.eugene.tapbymap.extensions

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Eugene on 27.08.2020
 */

val Fragment.applicationContext: Context get() = activity!!.applicationContext
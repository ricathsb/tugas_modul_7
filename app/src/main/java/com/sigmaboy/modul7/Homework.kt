package com.sigmaboy.modul7

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Homework(
    var id : Int = 0,
    var title : String? = "",
    var description : String? = "",
    var date : String? = "",
) : Parcelable

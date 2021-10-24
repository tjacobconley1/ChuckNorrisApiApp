package com.cfmg.chucknorris.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuoteResponse(
    val value: String,
): Parcelable

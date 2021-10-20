package com.example.chucknorris.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class QuoteResponse(
    val value: String,
): Parcelable

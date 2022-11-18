package com.jefersonsalazar.test.testbold.features

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.jefersonsalazar.test.domain.entities.DayInfoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.math.RoundingMode

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

fun ImageView.bindImageUrl(
    url: String?, @DrawableRes placeholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    if (url.isNullOrBlank()) {
        setImageResource(placeholder)
        return
    }
    val completeUrl = if (url.contains("https:")) {
        url
    } else "https:$url"

    Glide.with(context)
        .load(completeUrl)
        .error(errorPlaceholder)
        .placeholder(placeholder)
        .into(this)
}

fun String.splitAndGetJustName(): String {
    return this.split(",")[0]
}

fun DayInfoDomain.getWeatherAverage(): String {
    val average = ((this.maxTempCelsius + this.minTempCelsius) / 2).toBigDecimal()
        .setScale(1, RoundingMode.UP).toDouble()
    return "${average}º"
}
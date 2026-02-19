package ru.netology.weatheringwithyou.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.netology.weatheringwithyou.R
import ru.netology.weatheringwithyou.utils.WeatherUtils.CLEAR_SKY
import ru.netology.weatheringwithyou.utils.WeatherUtils.entries

enum class WeatherUtils(
    val descriptionFromServer: String,
    @DrawableRes val backgroundRes: Int,
    @DrawableRes val imageRes: Int?,
    @StringRes val weatherNameRes: Int,
) {
    CLEAR_SKY(
        descriptionFromServer = "clear sky",
        backgroundRes = R.drawable.clear_sky_gradient,
        imageRes = null,
        weatherNameRes = R.string.clear_sky
    ),
    FEW_CLOUDS(
        descriptionFromServer = "few clouds",
        backgroundRes = R.drawable.few_clouds_gradient,
        imageRes = R.drawable.few_clouds,
        weatherNameRes = R.string.few_clouds
    ),
    SCATTERED_CLOUDS(
        descriptionFromServer = "scattered clouds",
        backgroundRes = R.drawable.few_clouds_gradient,
        imageRes = R.drawable.few_clouds,
        weatherNameRes = R.string.scattered_clouds
    ),
    BROKEN_CLOUDS(
        descriptionFromServer = "broken clouds",
        backgroundRes = R.drawable.broken_clouds_gradient,
        imageRes = R.drawable.broken_clouds,
        weatherNameRes = R.string.broken_clouds
    ),
    SHOWER_RAIN(
        descriptionFromServer = "shower rain",
        backgroundRes = R.drawable.shower_rain_gradient,
        imageRes = R.drawable.shower_rain,
        weatherNameRes = R.string.shower_rain
    ),
    RAIN(
        descriptionFromServer = "rain",
        backgroundRes = R.drawable.rain_gradient,
        imageRes = R.drawable.rain,
        weatherNameRes = R.string.rain
    ),
    THUNDERSTORM(
        descriptionFromServer = "thunderstorm",
        backgroundRes = R.drawable.rain_gradient,
        imageRes = R.drawable.thunderstorm,
        weatherNameRes = R.string.thunderstorm
    ),
    SNOW(
        descriptionFromServer = "snow",
        backgroundRes = R.drawable.snow_gradient,
        imageRes = R.drawable.snow,
        weatherNameRes = R.string.snow
    ),
    MIST(
        descriptionFromServer = "mist",
        backgroundRes = R.drawable.mist_gradient,
        imageRes = R.drawable.mist,
        weatherNameRes = R.string.mist
    );
}

fun getWeatherByDescriptionFromServer(descriptionFromServer: String) =
    entries.firstOrNull { it.descriptionFromServer == descriptionFromServer } ?: CLEAR_SKY
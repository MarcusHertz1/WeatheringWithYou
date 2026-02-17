package ru.netology.weatheringwithyou.utils

import androidx.annotation.StringRes
import ru.netology.weatheringwithyou.R

enum class Language(@StringRes val languageRes: Int) {
    RUSSIAN(R.string.russian),
    ENGLISH(R.string.english)
}

enum class Theme(@StringRes val themeRes: Int) {
    LIGHT(R.string.light),
    DARK(R.string.dark)
}

enum class City(@StringRes val cityRes: Int, cityForApi: String) {
    VSEVOLOZSK(R.string.Vsevolozhsk, "vsevolozhsk,ru"),
    MOSCOW(R.string.moscow, "moscow,ru"),
    GRODNO(R.string.grodno, "grodno,by")
}

data class AppState(
    val city: City = City.MOSCOW,
    val theme: Theme = Theme.LIGHT,
    val language: Language = Language.RUSSIAN,
)

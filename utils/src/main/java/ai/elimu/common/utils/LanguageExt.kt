package ai.elimu.common.utils

import ai.elimu.model.v2.enums.Language
import java.util.Locale

fun Language.toLocale(): Locale {
    return when (this) {
        Language.ENG -> Locale.US
        Language.HIN -> Locale("hi", "IN")
        Language.TGL -> Locale("fil", "PH")
        Language.THA -> Locale("th", "TH")
        else -> Locale.US
    }
}

fun String.toLanguage(): Language {
    return when (this) {
        Language.ENG.isoCode -> Language.ENG
        Language.HIN.isoCode -> Language.HIN
        Language.TGL.isoCode -> Language.TGL
        Language.THA.isoCode -> Language.THA
        else -> Language.ENG
    }
}
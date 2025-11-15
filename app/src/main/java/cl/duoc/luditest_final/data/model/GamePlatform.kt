package cl.duoc.luditest_final.data.model

enum class GamePlatform(val displayName: String) {
    PC("PC"),
    PS4("PlayStation 4"),
    PS5("PlayStation 5"),
    XBOX_ONE("Xbox One"),
    XBOX_SERIES_X("Xbox Series X"),
    XBOX_SERIES_S("Xbox Series S"),
    SWITCH("Nintendo Switch"),
    SWITCH_2("Nintendo Switch 2"),
    SMARTPHONES("Smartphones"),
    MULTI_PLATFORM("Multiplataforma"),
    MAC("Mac"),
    LINUX("Linux"),
    ANDROID("Android"),
    IOS("iOS"),
    UNKNOWN("Plataforma no especificada");

    companion object {
        fun fromString(value: String): GamePlatform {
            return values().find { it.name.equals(value, ignoreCase = true) } ?: UNKNOWN
        }
    }
}
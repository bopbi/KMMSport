
object Versions {
    const val sqlDelight = "1.4.4"
    const val ktor = "1.4.3"
    const val kotlinxSerialization = "1.0.0-RC"
}

object Ktor {
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

}

object Serialization {
    const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}

object SqlDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val android = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"

    const val native = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
}


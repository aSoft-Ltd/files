plugins {
    id("com.android.library") version "4.1.0"
    kotlin("multiplatform") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20"
    id("tz.co.asoft.library") version "0.0.7"
}

kotlin {
    universalLib()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:${versions.kotlinx.serialization}")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx.coroutines}")
            }
        }
    }
}
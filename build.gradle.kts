plugins {
    id("com.android.library") version "4.1.0"
    kotlin("multiplatform") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20"
    id("tz.co.asoft.library") version "1.0.0"
    id("io.codearte.nexus-staging") version "0.22.0"
    signing
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

aSoftLibrary(
    version = vers.asoft.files,
    description = "An exposer of files"
)
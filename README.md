# Files
![badge][badge-maven] ![badge][badge-mpp] ![badge][badge-android] ![badge][badge-js] ![badge][badge-jvm]

A multiplatform exposer of file

## Introduction
This library will give you basic access to `File` in common code 

## Samples
```kotlin
suspend fun checkFileThenReadBytes(file: File){
    if(file.extension()=="jpg") { println("File is jpg")}
    val content = file.readBytes() //suspends here
}
```

## Setup
```kotlin
dependencies {
    implementation("tz.co.asoft:files:0.0.1")
}
```

[badge-maven]: https://img.shields.io/maven-central/v/tz.co.asoft/test/1.0.1?style=flat
[badge-mpp]: https://img.shields.io/badge/kotlin-multiplatform-blue?style=flat
[badge-android]: http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-yellow.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-orange.svg?style=flat

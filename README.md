# About
Configuration of [Talaiot gradle plugin](https://github.com/cdsap/Talaiot/) with Delivery Club Development setup.
All build statistics will be sent on Delivery Club's analytics database for analysis.

This repo is a fork of https://github.com/icerockdev/icerock-talaiot-config-plugin

# Setup
in `settings.gradle.kts` (gradle 6.9+):
```kotlin
pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

plugins {
    id("dev.dc.gradle.talaiot") version("3.+")
}
```

# Development
In plugin catalog create a file `gradle.properties`  

add keys:  
```
influx.url=https://influx-mob-infra.dev.dcube.devmail.ru
influx.org=primary
influx.bucket=talaiot
influx.token=INSERT_TOKEN_HERE
messenger.webhook=https://qa-bot.dev.dcube.devmail.ru/tg/message
messenger.chat=
```

for values you could use [influxdata.com](https://www.influxdata.com/) service

before testing or Gradle Sync you should publish plugin to mavenLocal:
```shell
./gradlew -p plugin publishToMavenLocal
```

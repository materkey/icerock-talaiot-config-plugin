/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.gradle.talaiot

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import java.net.HttpURLConnection
import java.net.URL

@Serializable
data class Payload(
    val message: String,

    @SerialName("chat_ids")
    val chatId: String
)

suspend fun Exception.sendToMessenger(logger: Logger, gitUser: String?) {
    val messengerAlertUrl = BuildConfig.messengerWebHook
    val messengerChatId = BuildConfig.messengerChatId
    val payload = Json.encodeToString(
        serializer = Payload.serializer(),
        value = Payload(
            message = "$this\ngitUser: $gitUser",
            chatId = messengerChatId
        )
    )

    @Suppress("BlockingMethodInNonBlockingContext")
    withContext(Dispatchers.Default) {
        try {
            val request = URL(messengerAlertUrl).openConnection() as HttpURLConnection
            request.requestMethod = "POST"
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            request.doOutput = true
            request.outputStream.write(payload.toByteArray())
            val responseCode = request.responseCode
            logger.debug("Error reporting response code $responseCode")
        } catch (e: Exception) {
            logger.info("can't send error to messenger", e)
        }
    }
}

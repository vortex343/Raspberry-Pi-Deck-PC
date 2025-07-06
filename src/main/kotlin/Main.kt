package com.eliasraunig

import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable

@Serializable
data class ActionPayload(val action: String)

fun main() {
    embeddedServer(Netty, port = 5001) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            post("/trigger") {
                val payload = call.receive<ActionPayload>()
                println("Received action: ${payload.action}")

                val response = when (payload.action) {

                    "leave" -> leave()

                    "media_back" -> mediaBack()
                    "media_play" -> mediaPlay()
                    "media_forward" -> mediaForward()

                    "rgb_up" -> rgbUp()
                    "rgb_down" -> rgbDown()

                    else -> "ERROR"
                }

                call.respondText(response)
            }
        }
    }.start(wait = true)
}

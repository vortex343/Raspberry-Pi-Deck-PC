package com.eliasraunig

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

                when (payload.action) {
                    //TODO
                    "start" -> {
                        println("Action started")
                    }
                }

                call.respondText("OK")
            }
        }
    }.start(wait = true)
}

package com.kmm.weather

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Greeting {
    private val client = HttpClient()
    private val platform: Platform = getPlatform()

    // TODO: https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#prerequisites
    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        //return "Hello, ${platform.name}!"
        return response.bodyAsText()
    }
}
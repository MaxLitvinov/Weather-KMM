package com.kmm.weather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
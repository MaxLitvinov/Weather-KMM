package com.kmm.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpDto(
    @SerialName("status") val status: String,
    @SerialName("country") val country: String,
    @SerialName("countryCode") val countryCode: String,
    @SerialName("region") val region: String,
    @SerialName("regionName") val regionName: String,
    @SerialName("city") val city: String,
    @SerialName("zip") val zip: String,
    @SerialName("lat") val lat: Float,
    @SerialName("lon") val lon: Float,
    @SerialName("timezone") val timezone: String,
    @SerialName("isp") val isp: String,
    @SerialName("org") val org: String,
    @SerialName("as") val _as: String,
    @SerialName("query") val query: String
)
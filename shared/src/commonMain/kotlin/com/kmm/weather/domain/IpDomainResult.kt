package com.kmm.weather.domain

sealed class IpDomainResult {

    data class Success(
        val query: String,
        val status: String,
        val country: String,
        val countryCode: String,
        val region: String,
        val regionName: String,
        val city: String,
        val zip: String,
        val lat: Float,
        val lon: Float,
        val timezone: String,
        val isp: String,
        val org: String,
        val _as: String
    ) : IpDomainResult()

    data class Failure(val error: String?) : IpDomainResult()
}

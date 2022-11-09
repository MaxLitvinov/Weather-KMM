package com.kmm.weather.domain

import com.kmm.weather.data.IpDto
import com.kmm.weather.data.NetworkClient
import com.kmm.weather.domain.mapper.IpDtoMapper
import io.ktor.client.call.*
import io.ktor.client.request.*

class IpRepository constructor(
    private val networkClient: NetworkClient,
    private val mapper: IpDtoMapper
) {

    suspend fun fetchLocation(): IpDomainResult {
        val request = networkClient.getClient().get("http://ip-api.com/json")
        val ipDto = request.body<IpDto>()
        return mapper.mapToDomain(ipDto)
    }
}

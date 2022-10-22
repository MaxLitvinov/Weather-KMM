package com.kmm.weather.home_page.ip_api

import com.kmm.weather.network.NetworkClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class IpRepository constructor(
    override val di: DI
    /*private val api: IpApi,
    private val mapper: IpDtoMapper*/
) : DIAware {

    val networkClient: NetworkClient by instance()

    suspend fun fetchLocation(): IpDomainResult {
        val request = networkClient.getClient().get("http://ip-api.com/json")
        return with(request.body<IpApiResponse>()) {
            IpDomainResult.Success(
                query = query,
                status = status,
                country = country,
                countryCode = countryCode,
                region = region,
                regionName = regionName,
                city = city,
                zip = zip,
                lat = lat,
                lon = lon,
                timezone = timezone,
                isp = isp,
                org = org,
                _as = _as
            )
        }
    }
}

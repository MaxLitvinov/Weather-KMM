package com.kmm.weather.domain.mapper

import com.kmm.weather.data.IpDto
import com.kmm.weather.domain.IpDomainResult

class IpDtoMapper {

    fun mapToDomain(dto: IpDto) = with(dto) {
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

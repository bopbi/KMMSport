package com.bobbyprabowo.kmmsport.shared.repository.impl

import com.bobbyprabowo.kmmsport.shared.datasource.SportsAPI
import com.bobbyprabowo.kmmsport.shared.repository.SportsRepository
import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult

class SportsRepositoryImpl(private val sportsAPI: SportsAPI) : SportsRepository {
    override suspend fun searchTeam(keyword: String): TeamSearchResult {
        return sportsAPI.searchTeam(keyword)
    }
}
package com.bobbyprabowo.kmmsport.shared.repository

import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult

interface SportsRepository {

    suspend fun searchTeam(keyword: String): TeamSearchResult
}
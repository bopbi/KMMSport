package com.bobbyprabowo.kmmsport.shared.datasource

import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult

interface SportsAPI {

    suspend fun searchTeam(keyword: String): TeamSearchResult
}
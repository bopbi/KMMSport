package com.bobbyprabowo.kmmsport.shared.datasource

import com.badoo.reaktive.single.Single
import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult

interface SportsAPI {

    fun searchTeam(keyword: String): Single<TeamSearchResult>
}
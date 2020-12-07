package com.bobbyprabowo.kmmsport.shared.datasource

import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.SingleWrapper
import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult

interface SportsAPI {

    fun searchTeam(keyword: String): Single<TeamSearchResult>

    fun searchTeamWrap(keyword: String): SingleWrapper<TeamSearchResult>
}
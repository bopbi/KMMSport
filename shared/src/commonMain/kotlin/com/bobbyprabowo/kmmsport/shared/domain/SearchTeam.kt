package com.bobbyprabowo.kmmsport.shared.domain

import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult

interface SearchTeam {

    suspend fun execute(keyword: String): TeamSearchResult

    fun execute(keyword: String,  success: (TeamSearchResult) -> Unit, fail: (Throwable) -> Unit)
}
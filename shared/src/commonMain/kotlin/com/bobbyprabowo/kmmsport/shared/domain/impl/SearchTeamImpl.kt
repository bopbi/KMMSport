package com.bobbyprabowo.kmmsport.shared.domain.impl

import com.bobbyprabowo.kmmsport.shared.domain.SearchTeam
import com.bobbyprabowo.kmmsport.shared.repository.SportsRepository
import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchTeamImpl(private val sportsRepository: SportsRepository) : SearchTeam {
    override suspend fun execute(keyword: String): TeamSearchResult {
        return sportsRepository.searchTeam(keyword)
    }

    override fun execute(keyword: String, success: (TeamSearchResult) -> Unit, fail: (Throwable) -> Unit) {
        MainScope().launch {
            try {
                success(sportsRepository.searchTeam(keyword))
            } catch (error: Throwable) {
                fail(error)
            }

        }
    }
}
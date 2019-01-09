package com.raga.football.entity.repository

import com.raga.football.entity.FootballMatch
import com.raga.football.entity.SearchedMatches
import com.raga.football.entity.Teams
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 01/09/18.
 */
interface MatchRepository {

    fun getFootballMatch(id : String) : Flowable<FootballMatch>

    fun getUpcomingMatch(id : String) : Flowable<FootballMatch>

    fun getEventById(id: String) : Flowable<FootballMatch>

    fun searchMatches(query: String?) : Flowable<SearchedMatches>
}
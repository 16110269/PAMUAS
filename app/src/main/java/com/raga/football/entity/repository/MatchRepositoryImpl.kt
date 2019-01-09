package com.raga.football.entity.repository

import com.raga.football.entity.FootballMatch
import com.raga.football.entity.Teams
import com.raga.football.rest.FootballRest
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 01/09/18.
 */
class MatchRepositoryImpl(private val footballRest: FootballRest) : MatchRepository {

    override fun searchMatches(query: String?) = footballRest.searchMatches(query)

    override fun getEventById(id: String): Flowable<FootballMatch> = footballRest.getEventById(id)

    override fun getUpcomingMatch(id: String): Flowable<FootballMatch> = footballRest.getUpcomingMatch(id)

    override fun getFootballMatch(id: String): Flowable<FootballMatch> = footballRest.getLastmatch(id)
}
package com.raga.football.entity.repository

import com.raga.football.entity.Teams
import com.raga.football.rest.FootballRest
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 10/09/18.
 */
class TeamRepositoryImpl(val footballRest: FootballRest) : TeamRepository{

    override fun getAllTeam(id: String) = footballRest.getAllTeam(id)
    override fun getTeamBySearch(query: String) = footballRest.getTeamBySearch(query)
    override fun getTeams(id: String): Flowable<Teams> = footballRest.getAllTeam(id)
    override fun getTeamsDetail(id: String): Flowable<Teams> = footballRest.getTeam(id)

}
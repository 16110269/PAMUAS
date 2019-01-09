package com.raga.football.entity.repository

import com.raga.football.entity.Team
import com.raga.football.entity.Teams
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 10/09/18.
 */
interface TeamRepository {

    fun getTeams(id : String = "0") : Flowable<Teams>

    fun getTeamsDetail(id : String = "0") : Flowable<Teams>

    fun getTeamBySearch(query: String) : Flowable<Teams>

    fun getAllTeam(id: String) : Flowable<Teams>
}
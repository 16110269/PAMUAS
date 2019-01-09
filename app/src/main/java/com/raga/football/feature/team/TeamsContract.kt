package com.raga.football.feature.team

import com.raga.football.entity.Team

/**
 * Created by muhrahmatullah on 10/09/18.
 */
interface TeamsContract {
    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()

    }
    interface Presenter{
        fun getTeamData(leagueName: String)
        fun searchTeam(teamName: String)
        fun onDestroy()
    }
}
package com.raga.football.feature.favteam

import com.raga.football.entity.Team

/**
 * Created by muhrahmatullah on 14/09/18.
 */
interface FavoriteTeamContract {

    interface View{
        fun displayTeams(teamList: List<Team>)
        fun hideLoading()
        fun showLoading()
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getTeamData()
        fun onDestroy()
    }
}
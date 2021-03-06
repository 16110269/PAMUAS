package com.raga.football.feature.lastmatch

import com.raga.football.entity.Event

/**
 * Created by muhrahmatullah on 03/09/18.
 */
interface MatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Event>)
    }

    interface Presenter{
        fun getFootballMatchData(leagueName: String = "4328")
        fun onDestroyPresenter()

    }
}
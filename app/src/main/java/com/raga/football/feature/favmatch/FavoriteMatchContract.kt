package com.raga.football.feature.favmatch

import com.raga.football.entity.Event

/**
 * Created by muhrahmatullah on 03/09/18.
 */
interface FavoriteMatchContract {
    interface View{
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList:List<Event>)
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getFootballMatchData()
        fun onDestroyPresenter()
    }
}
package com.raga.football.feature.searchmatches

import com.raga.football.entity.Event

/**
 * Created by muhrahmatullah on 15/09/18.
 */
interface SearchMatchContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayMatch(matchList: List<Event>)
    }
    interface Presenter{
        fun searchMatch(query: String?)
        fun onDestroy()
    }

}
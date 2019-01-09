package com.raga.football.feature.detail

import com.raga.football.entity.Team
import com.raga.football.entity.db.FavoriteMatch

/**
 * Created by muhrahmatullah on 01/09/18.
 */
interface DetailContract {

    interface View{
        fun displayTeamBadgeHome(team: Team)
        fun displayTeamBadgeAway(team: Team)
        fun setFavoriteState(favList:List<FavoriteMatch>)
    }

    interface Presenter{
        fun getTeamsBadgeAway(id:String)
        fun getTeamsBadgeHome(id:String)
        fun deleteMatch(id:String)
        fun checkMatch(id:String)
        fun insertMatch(eventId: String, homeId: String, awayId: String)
        fun onDestroyPresenter()
    }
}
package com.raga.football.feature.teamdetail

import com.raga.football.entity.db.FavoriteTeam

/**
 * Created by muhrahmatullah on 12/09/18.
 */
interface TeamDetailContract {

    interface View{
        fun setFavoriteState(favList:List<FavoriteTeam>)
    }

    interface Presenter{
        fun deleteTeam(id:String)
        fun checkTeam(id:String)
        fun insertTeam(id: String, imgUrl: String)
    }
}
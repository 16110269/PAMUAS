package com.raga.football.feature.players

import com.raga.football.entity.Player

/**
 * Created by muhrahmatullah on 13/09/18.
 */
interface PlayersContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun displayPlayers(playerList: List<Player>)

    }
    interface Presenter{
        fun getAllPlayer(teamId: String?)
        fun onDestroy()
    }

}
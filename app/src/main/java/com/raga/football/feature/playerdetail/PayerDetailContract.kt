package com.raga.football.feature.playerdetail

import com.raga.football.entity.Player

/**
 * Created by muhrahmatullah on 13/09/18.
 */
interface PayerDetailContract {

    interface View{
        fun displayPlayerDetail(player: Player)
    }
    interface Presenter{
        fun getPlayerData(idPlayer: String)
        fun onDestroy()
    }
}
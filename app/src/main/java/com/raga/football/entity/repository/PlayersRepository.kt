package com.raga.football.entity.repository

import com.raga.football.entity.FootballPlayer
import com.raga.football.entity.PlayerDetail
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 13/09/18.
 */
interface PlayersRepository {

    fun getAllPlayers(teamId: String?) : Flowable<FootballPlayer>
    fun getPlayerDetail(playerId: String?) : Flowable<PlayerDetail>
}
package com.raga.football.feature.players

import com.raga.football.entity.*
import com.raga.football.entity.repository.MatchRepositoryImpl
import com.raga.football.entity.repository.PlayersRepositoryImpl
import com.raga.football.feature.lastmatch.LastMatchPresenter
import com.raga.football.feature.lastmatch.MatchContract
import com.raga.football.utils.SchedulerProvider
import com.raga.football.utils.TestSchedulerProvider
import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by muhrahmatullah on 15/09/18.
 */
class PlayersPresenterTest {

    @Mock
    lateinit var mView: PlayersContract.View

    @Mock
    lateinit var playersRepositoryImpl: PlayersRepositoryImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: PlayersPresenter

    lateinit var player : FootballPlayer

    lateinit var playersDetail: Flowable<FootballPlayer>

    private val playerList = mutableListOf<Player>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        player = FootballPlayer(playerList)
        playersDetail = Flowable.just(player)
        mPresenter = PlayersPresenter(mView, playersRepositoryImpl, scheduler)
        `when`(playersRepositoryImpl.getAllPlayers("4328")).thenReturn(playersDetail)

    }

    @Test
    fun getAllPlayer() {
        mPresenter.getAllPlayer("4328")
        verify(mView).showLoading()
        verify(mView).displayPlayers(playerList)
        verify(mView).hideLoading()
    }
}
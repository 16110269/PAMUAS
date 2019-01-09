package com.raga.football.feature.players

import com.raga.football.entity.FootballPlayer
import com.raga.football.entity.PlayerDetail
import com.raga.football.entity.repository.PlayersRepositoryImpl
import com.raga.football.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

/**
 * Created by muhrahmatullah on 13/09/18.
 */
class PlayersPresenter(val mView: PlayersContract.View,
                       val playersRepositoryImpl: PlayersRepositoryImpl,
                       val schedulerProvider: SchedulerProvider): PlayersContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAllPlayer(teamId: String?) {
        mView.showLoading()
        compositeDisposable.add(playersRepositoryImpl.getAllPlayers(teamId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<FootballPlayer>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: FootballPlayer) {
                        mView.displayPlayers(t.player)
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayPlayers(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }
    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
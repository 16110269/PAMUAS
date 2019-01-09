package com.raga.football.feature.playerdetail

import com.raga.football.entity.PlayerDetail
import com.raga.football.entity.repository.PlayersRepositoryImpl
import com.raga.football.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by muhrahmatullah on 14/09/18.
 */
class PlayerDetailPresenter(val mView: PayerDetailContract.View,
                            val playersRepositoryImpl: PlayersRepositoryImpl,
                            val schedulerProvider: SchedulerProvider): PayerDetailContract.Presenter {


    val compositeDisposable = CompositeDisposable()

    override fun getPlayerData(idPlayer: String) {
        compositeDisposable.add(playersRepositoryImpl.getPlayerDetail(idPlayer)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<PlayerDetail>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: PlayerDetail) {
                        mView.displayPlayerDetail(t.player[0])
                    }

                    override fun onError(t: Throwable?) {

                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
package com.raga.football.feature.detail

import android.util.Log
import com.raga.football.entity.Team
import com.raga.football.entity.Teams
import com.raga.football.entity.repository.LocalRepositoryImpl
import com.raga.football.entity.repository.MatchRepositoryImpl
import com.raga.football.entity.repository.TeamRepositoryImpl
import com.raga.football.feature.main.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by muhrahmatullah on 01/09/18.
 */
class DetailPresenter(val mView : DetailContract.View, val teamRepositoryImpl: TeamRepositoryImpl,
                      val localRepositoryImpl: LocalRepositoryImpl) : DetailContract.Presenter {
    override fun deleteMatch(id: String) {
        localRepositoryImpl.deleteData(id)
    }

    override fun checkMatch(id: String) {
        mView.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    override fun insertMatch(eventId: String, homeId: String, awayId: String) {
        localRepositoryImpl.insertData(eventId, homeId, awayId)
    }

    override fun getTeamsBadgeHome(id: String) {
        compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object: ResourceSubscriber<Teams>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: Teams) {
                        mView.displayTeamBadgeHome(t.teams[0])
                    }

                    override fun onError(t: Throwable?) {

                    }
                })
        )
    }

    val compositeDisposable = CompositeDisposable()

    override fun getTeamsBadgeAway(id:String) {
        compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object: ResourceSubscriber<Teams>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: Teams) {
                        mView.displayTeamBadgeHome(t.teams[0])
                    }

                    override fun onError(t: Throwable?) {

                    }
                })
                )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}
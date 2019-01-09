package com.raga.football.feature.favteam

import com.raga.football.entity.Team
import com.raga.football.entity.Teams
import com.raga.football.entity.repository.LocalRepositoryImpl
import com.raga.football.entity.repository.TeamRepositoryImpl
import com.raga.football.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

/**
 * Created by muhrahmatullah on 14/09/18.
 */
class FavoriteTeamPresenter(val mView: FavoriteTeamContract.View,
                            val localRepositoryImpl: LocalRepositoryImpl,
                            val teamRepositoryImpl: TeamRepositoryImpl,
                            val scheduler: SchedulerProvider): FavoriteTeamContract.Presenter {


    val compositeDisposable = CompositeDisposable()

    override fun getTeamData() {
        mView.showLoading()
        val teamList = localRepositoryImpl.getTeamFromDb()
        var teamLists: MutableList<Team> = mutableListOf()
        for (fav in teamList){
            compositeDisposable.add(teamRepositoryImpl.getTeamsDetail(fav.idTeam)
                    .observeOn(scheduler.ui())
                    .subscribeOn(scheduler.io())
                    .subscribeWith(object: ResourceSubscriber<Teams>(){
                        override fun onComplete() {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                        }

                        override fun onNext(t: Teams) {
                            teamLists.add(t.teams[0])
                            mView.displayTeams(teamLists)
                        }

                        override fun onError(t: Throwable?) {
                            mView.hideLoading()
                            mView.hideSwipeRefresh()
                            mView.displayTeams(Collections.emptyList())
                        }

                    })
            )
        }

        if(teamList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayTeams(teamLists)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}
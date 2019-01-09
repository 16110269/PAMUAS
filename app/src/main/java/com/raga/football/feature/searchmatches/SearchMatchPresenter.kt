package com.raga.football.feature.searchmatches

import com.raga.football.entity.FootballMatch
import com.raga.football.entity.SearchedMatches
import com.raga.football.entity.repository.MatchRepositoryImpl
import com.raga.football.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

/**
 * Created by muhrahmatullah on 15/09/18.
 */
class SearchMatchPresenter(val mView: SearchMatchContract.View,
                           val matchRepositoryImpl: MatchRepositoryImpl,
                           val schedulerProvider: SchedulerProvider): SearchMatchContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(matchRepositoryImpl.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object: ResourceSubscriber<SearchedMatches>(){
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: SearchedMatches) {
                        mView.displayMatch(t.events ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayMatch(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }


}
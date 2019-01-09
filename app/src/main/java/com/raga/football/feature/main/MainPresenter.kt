package com.raga.football.feature.main

import com.raga.football.entity.Event
import com.raga.football.entity.Teams
import com.raga.football.entity.repository.MatchRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by muhrahmatullah on 01/09/18.
 */
class MainPresenter(val mView : MainContract.View, val matchRepositoryImpl: MatchRepositoryImpl) : MainContract.Presenter{

    val compositeDisposable = CompositeDisposable()
}
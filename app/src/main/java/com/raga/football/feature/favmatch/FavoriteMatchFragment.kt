package com.raga.football.feature.favmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raga.football.R
import com.raga.football.adapter.ClubAdapter
import com.raga.football.entity.Event
import com.raga.football.entity.repository.LocalRepositoryImpl
import com.raga.football.entity.repository.MatchRepositoryImpl
import com.raga.football.extensions.hide
import com.raga.football.extensions.show
import com.raga.football.feature.lastmatch.MatchContract
import com.raga.football.rest.FootballApiService
import com.raga.football.rest.FootballRest
import com.raga.football.utils.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_favorite_match.*

class FavoriteMatchFragment : Fragment(), FavoriteMatchContract.View {
    private var matchLists : MutableList<Event> = mutableListOf()
    lateinit var mPresenter : FavoriteMatchPresenter

    override fun hideLoading() {
        mainProgressBar.hide()
        rvFootball.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvFootball.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<Event>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootball.layoutManager = layoutManager
        rvFootball.adapter = ClubAdapter(matchList, context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient().create(FootballRest::class.java)
        val request = MatchRepositoryImpl(service)
        val localRepositoryImpl = LocalRepositoryImpl(context!!)
        val scheduler = AppSchedulerProvider()
        mPresenter = FavoriteMatchPresenter(this, request, localRepositoryImpl, scheduler)
        mPresenter.getFootballMatchData()

        swiperefreshFav.setOnRefreshListener {
            mPresenter.getFootballMatchData()
        }

    }

    override fun hideSwipeRefresh() {
        swiperefreshFav.isRefreshing = false
        mainProgressBar.hide()
        rvFootball.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }


}

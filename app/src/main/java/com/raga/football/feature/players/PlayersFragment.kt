package com.raga.football.feature.players


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.raga.football.R
import com.raga.football.adapter.PlayerAdapter
import com.raga.football.adapter.TeamAdapter
import com.raga.football.entity.Player
import com.raga.football.entity.Team
import com.raga.football.entity.repository.MatchRepositoryImpl
import com.raga.football.entity.repository.PlayersRepositoryImpl
import com.raga.football.extensions.hide
import com.raga.football.extensions.show
import com.raga.football.feature.lastmatch.LastMatchPresenter
import com.raga.football.rest.FootballApiService
import com.raga.football.rest.FootballRest
import com.raga.football.utils.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_players.*
import kotlinx.android.synthetic.main.fragment_teams.*

class PlayersFragment : Fragment(), PlayersContract.View {

    private var listPlayer : MutableList<Player> = mutableListOf()
    lateinit var mPresenter: PlayersContract.Presenter

    override fun showLoading() {
        playerProgressbar.show()
        rvPlayer.visibility = View.GONE
    }

    override fun hideLoading() {
        playerProgressbar.hide()
        rvPlayer.visibility = View.VISIBLE
    }

    override fun displayPlayers(playerList: List<Player>) {
        listPlayer.clear()
        listPlayer.addAll(playerList)
        val layoutManager = GridLayoutManager(context, 3)
        rvPlayer.layoutManager = layoutManager
        rvPlayer.adapter = PlayerAdapter(listPlayer, context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team: Team? = arguments?.getParcelable("teams")
        val service = FootballApiService.getClient().create(FootballRest::class.java)
        val request = PlayersRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = PlayersPresenter(this, request, scheduler)
        mPresenter.getAllPlayer(team?.idTeam)

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }


}

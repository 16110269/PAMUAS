package com.raga.football.feature.team


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.raga.football.R
import com.raga.football.adapter.TeamAdapter
import com.raga.football.entity.Team
import com.raga.football.entity.repository.TeamRepositoryImpl
import com.raga.football.extensions.hide
import com.raga.football.extensions.show
import com.raga.football.rest.FootballApiService
import com.raga.football.rest.FootballRest
import com.raga.football.utils.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_teams.*


class TeamsFragment : Fragment(), TeamsContract.View {

    lateinit var leagueName : String
    lateinit var mPresenter : TeamsContract.Presenter

    private var teamLists : MutableList<Team> = mutableListOf()

    override fun displayTeams(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = GridLayoutManager(context, 3)
        rvTeam.layoutManager = layoutManager
        rvTeam.adapter = TeamAdapter(teamLists, context)
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvTeam.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvTeam.visibility = View.INVISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballApiService.getClient().create(FootballRest::class.java)
        val request = TeamRepositoryImpl(service)
        val scheduler = AppSchedulerProvider()
        setHasOptionsMenu(true)
        mPresenter = TeamsPresenter(this, request, scheduler)
        mPresenter.getTeamData("4328")
        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerTeam.adapter = spinnerAdapter
        spinnerTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinnerTeam.selectedItem.toString()
                when(leagueName){
                    "English Premier League" -> mPresenter.getTeamData("4328")
                    "German Bundesliga" -> mPresenter.getTeamData("4331")
                    "Italian Serie A" -> mPresenter.getTeamData("4332")
                    "French Ligue 1" -> mPresenter.getTeamData("4334")
                    "Spanish La Liga" -> mPresenter.getTeamData("4335")
                    "Netherlands Eredivisie" -> mPresenter.getTeamData("4337")
                    else -> mPresenter.getTeamData("4328")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mPresenter.searchTeam(newText)
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                mPresenter.getTeamData("4328")
                return true
            }
        })
    }
}

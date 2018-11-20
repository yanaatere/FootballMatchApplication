package com.retere.assosiate.footbalmatchapplication.detail.team.fragment.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.player.DetailPlayerActivity
import com.retere.assosiate.footbalmatchapplication.detail.team.fragment.OverviewTeamFragment
import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItem
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class PlayerTeamFragment : Fragment(), PlayerTeamView {
    private var player: MutableList<PlayersItem> = mutableListOf()
    private lateinit var recycleView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var playerTeamAdapter: PlayerTeamAdapter
    private lateinit var playerTeamPresenter: PlayerTeamPresenter

    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_fragment_player_team, container, false)
        recycleView = view.find(R.id.rv_player_team)
        progressBar = view.find(R.id.id_progressbar_team_player)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idPlayer: String? = arguments?.getString(OverviewTeamFragment.ARG_PARAM1)

        recycleView.layoutManager = LinearLayoutManager(activity)

        val apiService = ApiService()
        val gson = Gson()

        playerTeamPresenter = PlayerTeamPresenter(this,apiService,gson)
        playerTeamPresenter.getListPlayer(idPlayer!!)

        playerTeamAdapter = PlayerTeamAdapter(player) {
            startActivity<DetailPlayerActivity>("idPlayer" to "${it.idPlayer}")
        }
        recycleView.adapter = playerTeamAdapter
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): PlayerTeamFragment {
            val fragment = PlayerTeamFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showList(data: List<PlayersItem>?) {
        if (data != null) {
            player.addAll(data)
        }
        playerTeamAdapter.notifyDataSetChanged()
    }

}
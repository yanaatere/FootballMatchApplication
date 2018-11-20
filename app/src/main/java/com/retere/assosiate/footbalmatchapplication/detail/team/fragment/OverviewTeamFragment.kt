package com.retere.assosiate.footbalmatchapplication.detail.team.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.team.DetailPresenterTeam
import com.retere.assosiate.footbalmatchapplication.detail.team.DetailTeamView
import com.retere.assosiate.footbalmatchapplication.detail.team.model.TeamsItem
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import org.jetbrains.anko.find

class OverviewTeamFragment : Fragment(), DetailTeamView {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var team: TeamsItem
    private lateinit var detailPresenterTeam: DetailPresenterTeam
    private var idTeam: String? = null

    private lateinit var mParam1: String
    private lateinit var mParam2: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_fragment_oberview_team, container, false)
        textView = view.find(R.id.tv_overview_team)
        progressBar = view.find(R.id.id_progressbar_overview_team)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idTeam = arguments?.getString(ARG_PARAM1)
        team = TeamsItem()
        val request = ApiService()
        val gson = Gson()
        detailPresenterTeam = DetailPresenterTeam(this,request,gson)
        detailPresenterTeam.getTeam(idTeam!!)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    companion object {

        val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"


        fun newInstance(param1: String, param2: String): OverviewTeamFragment {
            val fragment = OverviewTeamFragment()
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

    override fun showList(data: List<TeamsItem>) {
        val textOverviewTeam = data.get(0).strDescriptionEN
        textView.text = textOverviewTeam
    }
}
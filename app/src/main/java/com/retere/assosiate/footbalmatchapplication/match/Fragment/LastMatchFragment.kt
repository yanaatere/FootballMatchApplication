package com.retere.assosiate.footbalmatchapplication.match.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.match.DetailMatchActivity
import com.retere.assosiate.footbalmatchapplication.match.MatchAdapter
import com.retere.assosiate.footbalmatchapplication.match.MatchPresenter
import com.retere.assosiate.footbalmatchapplication.match.MatchView
import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchModel
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.fragment_layout_match.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class LastMatchFragment : Fragment(), MatchView {
    private lateinit var progressBar: ProgressBar
    private lateinit var idSpinnerLeauge: Spinner
    private var present: MatchPresenter? = null
    private var adapterListDataMatch: MatchAdapter? = null
    private var liga: MutableList<DetailEventMatchModel> = mutableListOf()
    private lateinit var leagueName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutLiga: RecyclerView = rv_match_team_layouthome
        layoutLiga.layoutManager = LinearLayoutManager(activity)
        progressBar = progress_bar
        idSpinnerLeauge = spinner
        return inflater.inflate(R.layout.fragment_layout_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemSpinner = resources.getStringArray(R.array.array_league)
        val adapterSpinner = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, itemSpinner)

        val request = ApiService()
        val gson = Gson()

        idSpinnerLeauge.adapter = adapterSpinner

        present = MatchPresenter(this, request, gson)

        adapterListDataMatch = MatchAdapter(liga) {
           startActivity<DetailMatchActivity>("idEvent" to "${it.idEvent}")
        }

        rv_match_team_layouthome.adapter = adapterListDataMatch

        idSpinnerLeauge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                leagueName = idSpinnerLeauge.selectedItem.toString()

                when (position) {
                    0 -> leagueName = "4328"
                    1 -> leagueName = "4328"
                    2 -> leagueName = "4329"
                    3 -> leagueName = "4329"
                    4 -> leagueName = "4329"
                    5 -> leagueName = "4329"
                    6 -> leagueName = "4329"
                }
                present?.getNextMatch(leagueName)
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.invisible()

    }

    override fun showListMatch(data: List<DetailEventMatchModel>?) {
        Log.e("Tag", "FragmentLast :" + data)
        liga.clear()
        if (data != null) {
            liga.addAll(data)
        }
        adapterListDataMatch?.notifyDataSetChanged()
    }
}
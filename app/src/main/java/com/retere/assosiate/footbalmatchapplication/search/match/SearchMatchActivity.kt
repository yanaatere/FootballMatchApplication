package com.retere.assosiate.footbalmatchapplication.search.match

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.SearchView
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.match.DetailMatchActivity
import com.retere.assosiate.footbalmatchapplication.search.match.model.EventItem
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.layout_content_search_match.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    private lateinit var searchMatchPresenter: SearchMatchPresenter
    private var adapterList: SearchMatchAdapter? = null
    private var match: MutableList<EventItem> = mutableListOf()
    private var hasil: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        rv_list_match_search.layoutManager = LinearLayoutManager(this)
        val apiService = ApiService()
        val gson = Gson()

        searchMatchPresenter = SearchMatchPresenter(this, apiService, gson)

        id_search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                hasil = query!!
                if (query.length > 4) {
                    searchMatchPresenter.getTeamListMatch(hasil!!)
                    id_progressbar_content_search.invisible()
                    adapterList = SearchMatchAdapter(match) {
                        startActivity<DetailMatchActivity>("idEvent" to it.idEvent)
                    }

                    rv_list_match_search.adapter = adapterList
                }


                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun showLoading() {
        id_progressbar_content_search.visible()
    }

    override fun hideLoading() {
        id_progressbar_content_search.invisible()
    }

    override fun showList(data: List<EventItem>?) {
        Log.e("Tag", "search match :" + data)
        match.clear()
        if (data != null) {
            match.addAll(data)
        }
        adapterList?.notifyDataSetChanged()

    }
}

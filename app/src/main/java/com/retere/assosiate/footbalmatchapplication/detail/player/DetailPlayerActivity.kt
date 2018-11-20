package com.retere.assosiate.footbalmatchapplication.detail.player

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItemDetail
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.activity_detail_player.*
import kotlinx.android.synthetic.main.content_detail_activity_player.*

class DetailPlayerActivity : AppCompatActivity(),DetailPlayerView{

    private lateinit var detailPlayerPresenter:DetailPlayerPresenter
    private lateinit var idPlayer :String

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail_player)
        setSupportActionBar(toolbar)

        idPlayer = intent.getStringExtra("idPlayer")
        val request = ApiService()
        val gson = Gson()

        detailPlayerPresenter = DetailPlayerPresenter(this, request, gson)
        detailPlayerPresenter.getDetailPlayer(idPlayer)
    }

    override fun showLoading() {
        id_progressbar_detail_player.visible()
    }

    override fun hideLoading() {
        id_progressbar_detail_player.invisible()
    }

    override fun showList(data: List<PlayersItemDetail>?) {
        val getImgPlayerDetail = data?.get(0)?.strThumb
        val tvBeratPlayerDetail = data?.get(0)?.strWeight
        val tvTinggiPlayerDetail = data?.get(0)?.strHeight
        val tvDeskDetail = data?.get(0)?.strDescriptionEN
        Glide.with(this).load(getImgPlayerDetail).into(img_player_detail_activity)
        tv_position.text = data?.get(0)?.strPosition
        tv_berat_detail_player.text = tvBeratPlayerDetail
        tv_tinggi_detail_player.text = tvTinggiPlayerDetail
        tv_desc_detail_player.text = tvDeskDetail
    }

}
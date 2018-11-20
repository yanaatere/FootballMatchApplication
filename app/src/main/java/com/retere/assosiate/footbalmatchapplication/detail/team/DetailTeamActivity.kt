package com.retere.assosiate.footbalmatchapplication.detail.team

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.database.database
import com.retere.assosiate.footbalmatchapplication.detail.team.model.TeamsItem
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteTeamModel
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteTeamModel.Companion.TEAM_ID
import com.retere.assosiate.footbalmatchapplication.tabAdapter.TeamTabAdapter
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.content_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private var menuItem: Menu? = null
    private var isTeamFavorite: Boolean = false
    private lateinit var idTeam: String
    private lateinit var detailPresenterTeam: DetailPresenterTeam
    private lateinit var teamsItem: TeamsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(toolbar)
        idTeam = intent.getStringExtra("idTeam")
        teamsItem = TeamsItem()

        val request = ApiService()
        val gson = Gson()

        detailPresenterTeam = DetailPresenterTeam(this, request, gson)
        detailPresenterTeam.getTeam(idTeam)

        implementTab()
        showFavorite()
    }

    private fun showFavorite() {
        try {

            database.use {
                val hasildata = select(FavoriteTeamModel.TABLE_FAVORITE_TEAM)
                    .whereArgs("($TEAM_ID = {id})", "id" to idTeam)
                val teamFavorite = hasildata.parseList(classParser<FavoriteTeamModel>())
                isTeamFavorite = !teamFavorite.isEmpty()
                setFavorite(isTeamFavorite)
            }

        } catch (e: SQLiteConstraintException) {
            toast("Error Cek Favorite : $e.localizedMessage")
        }
    }

    private fun addedToFavorite() {
        try {
            database.use {
                insert(FavoriteTeamModel.TABLE_FAVORITE_TEAM,
                    FavoriteTeamModel.TEAM_ID to teamsItem.idTeam,
                    FavoriteTeamModel.TEAM_NAME to teamsItem.strTeam,
                    FavoriteTeamModel.TEAM_IMAGE to teamsItem.strTeamBadge
                )
            }
            toast("data berhasil disimpan")
        } catch (e: SQLiteConstraintException) {
            toast("data gagal di simpan :" + e.localizedMessage)
        }

    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeamModel.TABLE_FAVORITE_TEAM,
                    "($TEAM_ID = {id})", "id" to idTeam)
            }
            toast("hapus dari favorite ")

        } catch (e: SQLiteConstraintException) {
            toast("error Hapus :" + e.localizedMessage)
        }

    }

    private fun setFavorite(teamFavorite: Boolean) {
        if (isTeamFavorite) {
            menuItem?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun implementTab() {
        id_tablayout_detail.addTab(id_tablayout_detail.newTab().setText("Overview"))
        id_tablayout_detail.addTab(id_tablayout_detail.newTab().setText("Player"))

        val adapter = TeamTabAdapter(supportFragmentManager, idTeam)
        id_pager_detail.adapter = adapter
        id_pager_detail.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(id_tablayout_detail))
        id_tablayout_detail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    id_pager_detail.currentItem = tab.position
                }
            }

        })
    }

    override fun showLoading() {
        id_progressbar_detail_team.visible()
    }

    override fun hideLoading() {
        id_progressbar_detail_team.invisible()
    }

    override fun showList(data: List<TeamsItem>) {
        val teamItem = data.get(0)
        val imgGambarDetailTeam = teamItem.idTeam
        val yearOfTeam = teamItem.intFormedYear
        val stadiumOfTeam = teamItem.strStadium

        tv_thn_detail_team.text = yearOfTeam
        tv_stadium_detail_team.text = stadiumOfTeam
        Glide.with(this).load(imgGambarDetailTeam).into(img_detail_gambar_team)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_team, menu)
        menuItem = menu
        setFavorite(isTeamFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favoriteTeam -> {
                if (isTeamFavorite) {
                    removeFromFavorite()
                    isTeamFavorite = false
                } else {
                    addedToFavorite()
                    isTeamFavorite = true
                }
                setFavorite(isTeamFavorite)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
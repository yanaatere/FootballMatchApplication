package com.retere.assosiate.footbalmatchapplication.detail.match

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.database.database
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteMatchModel
import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchModel
import com.retere.assosiate.footbalmatchapplication.model.ImageTeamsModel
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.detail_activity_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    private lateinit var imageHomeTeam: ImageView
    private lateinit var imageAwayTeam: ImageView
    private lateinit var textScoreHomeTeam: TextView
    private lateinit var textScoreAwayTeam: TextView
    private lateinit var textGoalHomeTeam: TextView
    private lateinit var tvGoalAwayTeam: TextView
    private lateinit var idEvent: String


    var detailPresenter: PresenterDetailMatch? = null
    var idHomeTeam: String? = null
    var idAwayTeam: String? = null
    var eventMatch: DetailEventMatchModel? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail_activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Match"

        val apiService = ApiService()
        val gson = Gson()
        idEvent = intent.getStringExtra("idEvent")
        detailPresenter = PresenterDetailMatch(this, apiService, gson)
        imageHomeTeam = imgHomeLogo
        textScoreHomeTeam = homeGoalDetail
        imageAwayTeam = imgAwayLogo
        textScoreAwayTeam = awayGoalDetail
        showFavorite()
    }

    override fun showLoading() {
        pd_detail_activity.visible()
    }

    override fun hideLoading() {
        pd_detail_activity.invisible()
    }

    override fun showList(data: List<DetailEventMatchModel>?) {
        var dF = SimpleDateFormat("yyyy-MM-dd")
        var date = dF.parse(data?.get(0)?.dateEvent)
        dF = SimpleDateFormat("EEE, dd MMMM yyyy")

        //Jam
        tvDate.text = dF.format(date).toString()

        val calendar = Calendar.getInstance(
            TimeZone.getTimeZone("GMT+7"),
            Locale.ENGLISH
        )

        val currentLocalTime = calendar.time
        val time = SimpleDateFormat("ZZZZZ")

        tvTime.text = time.format(currentLocalTime)

        eventMatch = data?.get(0)
        idHomeTeam = data?.get(0)?.idHomeTeam
        idAwayTeam = data?.get(0)?.idAwayTeam
        textScoreHomeTeam.text = data?.get(0)?.intHomeScore
        textScoreAwayTeam.text = data?.get(0)?.intAwayScore
        textGoalHomeTeam.text = data?.get(0)?.strHomeGoalDetails
        tvGoalAwayTeam.text = data?.get(0)?.strAwayGoalDetails

        //Team
        tvHomeTeam.text = data?.get(0)?.strHomeTeam
        tvAwayTeam.text = data?.get(0)?.strAwayTeam

        //Keeper
        homeGoalKeeper.text = data?.get(0)?.strHomeLineupGoalkeeper
        awayGoalKeeper.text = data?.get(0)?.strAwayLineupGoalkeeper

        //Defense
        homeDefense.text = data?.get(0)?.strHomeLineupDefense
        awayDefense.text = data?.get(0)?.strAwayLineupDefense

        //Center
        homeMidfield.text = data?.get(0)?.strHomeLineupMidfield
        awayMidfield.text = data?.get(0)?.strAwayLineupMidfield

        //Forward
        homeForward.text = data?.get(0)?.strHomeLineupForward
        awayForward.text = data?.get(0)?.strAwayLineupForward

        //Subtitution
        homeSubstitution.text = data?.get(0)?.strHomeLineupSubstitutes
        awaySubstitution.text = data?.get(0)?.strAwayLineupSubstitutes

        detailPresenter?.getImgHomeTeam(idHomeTeam.toString())
        detailPresenter?.getImgAwayTeam(idAwayTeam.toString())
    }

    override fun awayImg(eachTeam: List<ImageTeamsModel>?) {
        val imgAway = eachTeam?.get(0)?.strTeamBadge
        Glide.with(this).load(imgAway).into(imageAwayTeam)
    }

    override fun homeImg(eachTeam: List<ImageTeamsModel>?) {
        val imgHome = eachTeam?.get(0)?.strTeamBadge
        Glide.with(this).load(imgHome).into(imageHomeTeam)
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteMatchModel.TABLE_FAVORITE_MATCH,
                    "(MATCH_ID = {id})", "id" to idEvent
                )
            }
            snackbar(layout_Detail, "Removed From Favorite Match ").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(layout_Detail, e.localizedMessage).show()
        }
    }

    private fun addedToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteMatchModel.TABLE_FAVORITE_MATCH,
                    FavoriteMatchModel.MATCH_ID to eventMatch?.idEvent,
                    FavoriteMatchModel.MATCH_DATE to eventMatch?.dateEvent,
                    FavoriteMatchModel.HOME_TEAM to eventMatch?.strHomeTeam,
                    FavoriteMatchModel.AWAY_TEAM to eventMatch?.strAwayTeam,
                    FavoriteMatchModel.SCORE_HOME to eventMatch?.intHomeScore,
                    FavoriteMatchModel.SCORE_AWAY to eventMatch?.intAwayScore
                )
            }
            snackbar(layout_Detail, "Added To Favorite Match ").show()

        } catch (e: SQLiteConstraintException) {
            Log.e("TAG", "error : ${e.localizedMessage}")
        }

    }


    private fun showFavorite() {
        try {
            database.use {
                val result = select(FavoriteMatchModel.TABLE_FAVORITE_MATCH)
                    .whereArgs("(MATCH_ID={id})", "id" to idEvent)
                val matchFavorite = result.parseList(classParser<FavoriteMatchModel>())
                isFavorite = !matchFavorite.isEmpty()
                setFavorite(isFavorite)
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun setFavorite(favorite: Boolean) {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavorite(isFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.home -> {
                finish()
                true
            }
            R.id.favoriteMatch -> {
                if (isFavorite) {
                    removeFromFavorite()
                    isFavorite = false
                } else {
                    addedToFavorite()
                    isFavorite = true
                }
                setFavorite(isFavorite)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


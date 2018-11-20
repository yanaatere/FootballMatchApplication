package com.retere.assosiate.footbalmatchapplication.detail.team.fragment.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerTeamAdapter(
    private val player: List<PlayersItem>,
    private val listener: (PlayersItem) -> Unit
) : RecyclerView.Adapter<PlayerTeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerTeamAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_player, p0, false))
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(p0: PlayerTeamAdapter.ViewHolder, p1: Int) {
        p0.bindItem(player[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamePlayer: TextView = view.find(R.id.tv_name_list_player)
        val tvPosisitionPlayer: TextView = view.find(R.id.tv_posisition_list_player)
        val imgPlayer: ImageView = view.find(R.id.img_team_list_player)


        fun bindItem(player: PlayersItem, listener: (PlayersItem) -> Unit) {
            tvNamePlayer.text = player.strPlayer
            tvPosisitionPlayer.text = player.strPosition
            //Ini Ganti Photo Player
            Picasso.get().load(player.strThumb).into(imgPlayer)
            itemView.onClick { listener(player) }
        }
    }
}

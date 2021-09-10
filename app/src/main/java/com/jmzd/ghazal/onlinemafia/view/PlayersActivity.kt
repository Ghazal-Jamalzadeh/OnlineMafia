package com.jmzd.ghazal.onlinemafia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.adapter.PlayersListAdapter
import com.jmzd.ghazal.onlinemafia.dataModel.PlayerInfoData
import com.jmzd.ghazal.onlinemafia.databinding.ActivityPlayersBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.viewModel.PlayersViewModel

class PlayersActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayersBinding
    lateinit var tableName:String
    lateinit var tablePass:String
    lateinit var playerName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        tableName = intent.getStringExtra("tableName").toString()
        tablePass= intent.getStringExtra("tablePass").toString()

        val viewmodel = ViewModelProvider(this, Factory(App())).get(PlayersViewModel::class.java)

        viewmodel.getPlayers(tableName,tablePass)

        viewmodel.mutable.observe(this , Observer { playersList-> //list<PlayersDataModel>
         //   Log.d("test" , it.toString())
            binding.recyclerView.also {
                it.layoutManager = LinearLayoutManager(this)
                val playersArrayList=ArrayList(playersList)
                val adapter = PlayersListAdapter(this, playersArrayList, object : PlayersListAdapter.GetChangeItems {
                    override fun getChange() {
                     // change count
                    }
                }, tableName,tablePass)
                it.adapter = adapter
            }
        })


    }
}
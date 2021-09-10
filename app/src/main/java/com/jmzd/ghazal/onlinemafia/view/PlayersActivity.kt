package com.jmzd.ghazal.onlinemafia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmzd.ghazal.onlinemafia.adapter.PlayersListAdapter
import com.jmzd.ghazal.onlinemafia.databinding.ActivityPlayersBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.viewModel.PlayersViewModel
import kotlin.math.log

class PlayersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayersBinding
    lateinit var tableName:String
    lateinit var tablePass:String
    lateinit var playerName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        tableName = intent.getStringExtra("tableName").toString()
        tablePass= intent.getStringExtra("tablePass").toString()

        val viewModel = ViewModelProvider(this, Factory(App())).get(PlayersViewModel::class.java)

        viewModel.getPlayers(tableName,tablePass)
        getCount(viewModel)
        getAdmin(viewModel)
        binding.tableNameTv.text= tableName

        viewModel.mutable.observe(this ,  { playersList-> //list<PlayersDataModel>
            binding.recyclerView.also {
                it.layoutManager = LinearLayoutManager(this)
                val playersArrayList=ArrayList(playersList)
                val adapter = PlayersListAdapter(this, playersArrayList, object : PlayersListAdapter.GetChangeItems {
                    override fun getChange() {
                     getCount(viewModel)
                    }
                }, tableName,tablePass, intent.getBooleanExtra("isAdmin",false) )
                it.adapter = adapter
            }
        })


    }

    fun getCount(viewModel : PlayersViewModel){
        viewModel.getCount(tableName,tablePass)
        viewModel.countMutabele.observe(this ,  {
            binding.playersCountTv.text= it.count
        })
    }

    fun getAdmin (viewModel : PlayersViewModel){
        viewModel.getAdmin(tableName,tablePass)
        viewModel.adminMutable.observe(this , {
            binding.adminNameTv.text=it.admin
        })
    }
}



package com.jmzd.ghazal.onlinemafia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.dataModel.PlayerInfoData
import com.jmzd.ghazal.onlinemafia.databinding.ActivityPlayersBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.viewModel.PlayersViewModel

class PlayersActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent

        val viewmodel = ViewModelProvider(this, Factory(App())).get(PlayersViewModel::class.java)
        var playerInfo = PlayerInfoData()

        viewmodel.getPlayers(intent.getStringExtra("tableName").toString(), intent.getStringExtra("tablePass").toString())

        viewmodel.mutable.observe(this , Observer {
            Log.d("test" , it.toString())
        })


    }
}
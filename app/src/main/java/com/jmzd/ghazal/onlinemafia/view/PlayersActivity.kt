package com.jmzd.ghazal.onlinemafia.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.adapter.PlayersListAdapter
import com.jmzd.ghazal.onlinemafia.databinding.ActivityPlayersBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.viewModel.PlayersViewModel

class PlayersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayersBinding
    lateinit var tableName:String
    lateinit var tablePass:String
    lateinit var playerName:String
    lateinit var viewModel:PlayersViewModel
    var isAdmin:Boolean = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        tableName = intent.getStringExtra("tableName").toString()
        tablePass= intent.getStringExtra("tablePass").toString()
        playerName= intent.getStringExtra("playerName").toString()
        isAdmin = intent.getBooleanExtra("isAdmin",false)

        viewModel = ViewModelProvider(this, Factory(App())).get(PlayersViewModel::class.java)

        getPlayers()
        getCount()
        getAdmin()
        binding.tableNameTv.text= tableName

        binding.refresh.setOnClickListener(){
            getPlayers()
            getCount()
            getAdmin()
        }
        //TODO: change this
        binding.exit.setOnClickListener(){
            alertDialogBuilder()
            }
        }





    fun getPlayers (){
        viewModel.getPlayers(tableName,tablePass)
        viewModel.mutable.observe(this ,  { playersList-> //list<PlayersDataModel>
            //TODO: احتمالا باید در صورت خالی بودن لیست دریافتی مدیریت شود قضیه
            binding.recyclerView.also {
                it.layoutManager = LinearLayoutManager(this)
                val playersArrayList=ArrayList(playersList)
                val adapter = PlayersListAdapter(this, playersArrayList, object : PlayersListAdapter.GetChangeItems {
                    override fun getChange() {
                        getCount()
                    }
                }, tableName,tablePass, intent.getBooleanExtra("isAdmin",false) )
                it.adapter = adapter
            }
        })

    }

    fun getCount(){
        viewModel.getCount(tableName,tablePass)
        viewModel.countMutabele.observe(this ,  {
            binding.playersCountTv.text= it.count
        })
    }

    fun getAdmin (){
        viewModel.getAdmin(tableName,tablePass)
        viewModel.adminMutable.observe(this , {
            binding.adminNameTv.text=it.admin
        })
    }

    fun alertDialogBuilder(){
        val builder = AlertDialog.Builder(this,R.style.AlertDialog)
        //set title for alert dialog
        builder.setTitle(R.string.dialog_title)
        //set message for alert dialog
        if (isAdmin) {
            builder.setMessage(R.string.admin_dialog_message)
        }else{
            builder.setMessage(R.string.player_dialog_message)
        }

        builder.setIcon(R.mipmap.alert1_round)

        //performing positive action
        if(isAdmin) {
            builder.setPositiveButton("خروج و پاک کردن میز") { dialogInterface, which ->
                viewModel.deleteTable(tableName,tablePass)
                finish()
            }
        }else{
            builder.setPositiveButton("ترک میز") { dialogInterface, which ->
                viewModel.deletePlayer(tableName,tablePass,playerName)
                finish()
            }

        }
        //performing cancel action
        builder.setNeutralButton("انصراف"){dialogInterface , which ->

        }
//        //performing negative action
//        builder.setNegativeButton("انصراف"){dialogInterface, which ->
//            Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
//        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onBackPressed() {
        alertDialogBuilder()
    }
    }




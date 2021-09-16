package com.jmzd.ghazal.onlinemafia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.databinding.ActivityCreateTableBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.repository.Repository
import com.jmzd.ghazal.onlinemafia.viewModel.CreateJoinViewModel

class CreateTableActivity : AppCompatActivity() {
    lateinit var bind : ActivityCreateTableBinding
    private val TAG = "LoginActivity"
     var isAdmin : Boolean = false
    var token :String? =null
    var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_table)

//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_create_table)

        val viewmodel = ViewModelProvider(this, Factory(App())).get(CreateJoinViewModel::class.java)
        bind.viewmodel=viewmodel

        token = Repository.SharedPreferences.getSharedPreferences(this , R.string.preference_token_key.toString())
        username = Repository.SharedPreferences.getSharedPreferences(this , R.string.preference_username_key.toString())

        Log.d(TAG , "token = $token , username = $username")

        var intent = intent

        if (intent.getStringExtra("mode")=="create"){
            bind.formButton.setText(R.string.create_table_button_text)
            isAdmin = true
        }else if (intent.getStringExtra("mode")=="join"){
            bind.formButton.setText(R.string.join_table_button_text)
        }

        bind.formButton.setOnClickListener(){
            Log.d(TAG , "button clicked...")
            if (intent.getStringExtra("mode")=="create"){
                Log.d(TAG , "create mode")
                viewmodel.createTable()
            }else if (intent.getStringExtra("mode")=="join"){
                Log.d(TAG , "join mode")
                viewmodel.joinTable()
            }
            viewmodel.mutable.observe(this , Observer {
                if(it.status.equals("ok")){
                    val intent = Intent(this, PlayersActivity::class.java)
                    intent.putExtra("tableName" , viewmodel.tableName.toString())
                    intent.putExtra("tablePass" , viewmodel.tablePass.toString())
                    intent.putExtra("playerName" , viewmodel.playerName.toString())
                    intent.putExtra("isAdmin" , isAdmin)
                    startActivity(intent)
                    finish()
                }else{
                    Log.d("test" , it.status.toString())
                }
            })
        }

//        viewmodel.mutable.observe(this , Observer {
//                if(it.status.equals("ok")){
//                    PlayerInfoData(viewmodel.tableName.toString() , viewmodel.tablePass.toString(), viewmodel.playerName.toString())
//                    val intent = Intent(this, PlayersActivity::class.java)
//                    startActivity(intent)
//                }
//            })

    }
}
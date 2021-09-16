package com.jmzd.ghazal.onlinemafia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.databinding.ActivityRegisterBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.repository.Repository
import com.jmzd.ghazal.onlinemafia.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var bind: ActivityRegisterBinding
    private  val TAG = "RegisterActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bind = DataBindingUtil.setContentView(this , R.layout.activity_register)

        val viewmodel = ViewModelProvider(this, Factory(App())).get(RegisterViewModel::class.java)
        bind.viewmodel=viewmodel

        bind.registerButton.setOnClickListener(){
            viewmodel.register()
        }

        viewmodel.mutable.observe(this ,  {
            Log.d("regTest" , it.toString())
            if(it.status=="ok"){
                Log.d(TAG , "ثبت نام با موفقیت انجام شد ")
                Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_token_key.toString(), it.token)
                Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_username_key.toString(), it.username)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else if (it.status=="error"){
                Log.d(TAG , "ثبت نام ناموفق. لطفا دوباره تلاش کنید")
            }else if (it.status=="duplicate username"){
                Log.d(TAG , "نام کاربری شما تکراری است. لطفا کمی آن را تغییر دهید.")
            }
        })

    }
}
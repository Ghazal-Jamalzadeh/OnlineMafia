package com.jmzd.ghazal.onlinemafia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  bind = DataBindingUtil.setContentView(this, R.layout.activity_login)


        var intent = intent
        if (intent.getStringExtra("mode")=="create"){
            binding.formButton.setText(R.string.create_table_button_text)
        }else if (intent.getStringExtra("mode")=="join"){
            binding.formButton.setText(R.string.join_table_button_text)
        }


    }
}
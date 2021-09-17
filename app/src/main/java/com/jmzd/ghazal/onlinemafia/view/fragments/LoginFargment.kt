package com.jmzd.ghazal.onlinemafia.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.databinding.FragmentLoginFargmentBinding
import com.jmzd.ghazal.onlinemafia.databinding.FragmentRegisterBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.repository.Repository
import com.jmzd.ghazal.onlinemafia.repository.SnackBarMaker
import com.jmzd.ghazal.onlinemafia.view.MainActivity
import com.jmzd.ghazal.onlinemafia.viewModel.RegisterViewModel


class LoginFargment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val bind = DataBindingUtil.inflate<FragmentLoginFargmentBinding>(inflater,
            R.layout.fragment_login_fargment,container,false)

        val viewmodel = ViewModelProvider(this, Factory(App())).get(RegisterViewModel::class.java)
        bind.viewmodel=viewmodel

        bind.loginButton.setOnClickListener(){
            viewmodel.login(bind.root)
        }

        bind.RegisterButton.setOnClickListener(){
            //https://developer.android.com/guide/navigation/navigation-getting-started#kts
            val action = LoginFargmentDirections.actionLoginFargmentToRegisterFragment()
            view?.findNavController()?.navigate(action)
        }

        viewmodel.mutable.observe(viewLifecycleOwner,{
//            Log.d("regTest" , it.toString())
            if(it.status=="ok"){
                SnackBarMaker.SnackBar.setSnackBar(bind.root , R.string.login_successful.toString())

                requireActivity().run{
                    Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_token_key.toString(), it.token)
                    Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_username_key.toString(), it.username)

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

            }else if (it.status=="error"){
                SnackBarMaker.SnackBar.setSnackBar(bind.root , R.string.wrong_username_or_password.toString())
            }
        })

        return bind.root
    }


}
package com.jmzd.ghazal.onlinemafia.view.fragments


import android.content.Context
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
import com.jmzd.ghazal.onlinemafia.databinding.FragmentRegisterBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.repository.Repository
import com.jmzd.ghazal.onlinemafia.repository.SnackBarMaker
import com.jmzd.ghazal.onlinemafia.view.MainActivity
import com.jmzd.ghazal.onlinemafia.viewModel.RegisterViewModel


class RegisterFragment : Fragment() {
    private  val TAG = "RegisterFragment"

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val bind = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,
            R.layout.fragment_register,container,false)

        val viewmodel = ViewModelProvider(this, Factory(App())).get(RegisterViewModel::class.java)
        bind.viewmodel=viewmodel

        bind.registerButton.setOnClickListener(){

                viewmodel.register(bind.root)
        }

        bind.loginButton.setOnClickListener(){
            //https://developer.android.com/guide/navigation/navigation-getting-started#kts
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFargment()
            view?.findNavController()?.navigate(action)
        }

        viewmodel.mutable.observe(viewLifecycleOwner,  {
            Log.d("regTest" , it.toString())
            if(it.status=="ok"){
                SnackBarMaker.SnackBar.setSnackBar(bind.root , R.string.register_successful.toString())

                requireActivity().run{
                    Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_token_key.toString(), it.token)
                    Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_username_key.toString(), it.username)

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

            }else if (it.status=="error"){
                SnackBarMaker.SnackBar.setSnackBar(bind.root , R.string.register_unsuccessful.toString())
            }else if (it.status=="duplicate username"){
                SnackBarMaker.SnackBar.setSnackBar(bind.root , R.string.duplicate_username.toString())
            } else if (it.status=="duplicate email"){
                SnackBarMaker.SnackBar.setSnackBar(bind.root , R.string.duplicate_email.toString())
            }
        })
        return bind.root
    }

}
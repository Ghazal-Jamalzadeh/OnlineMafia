package com.jmzd.ghazal.onlinemafia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.databinding.ActivityRegisterBinding
import com.jmzd.ghazal.onlinemafia.repository.App
import com.jmzd.ghazal.onlinemafia.repository.Factory
import com.jmzd.ghazal.onlinemafia.repository.Repository
import com.jmzd.ghazal.onlinemafia.view.fragments.RegisterFragment
import com.jmzd.ghazal.onlinemafia.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var bind: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
//کار می کند ولی احتمالا قدیمی تر است
//        supportFragmentManager.beginTransaction().replace(R.id.RegisterFragmentContainerView,RegisterFragment()).commit()


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.RegisterFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
//        val binding = ActivityRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Get the navigation host fragment from this Activity
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.RegisterFragmentContainerView) as NavHostFragment
//        // Instantiate the navController using the NavHostFragment
//        navController = navHostFragment.navController
//        // Make sure actions in the ActionBar get propagated to the NavController
//        setupActionBarWithNavController(navController)



//        bind = DataBindingUtil.setContentView(this , R.layout.activity_register)
//
//        val viewmodel = ViewModelProvider(this, Factory(App())).get(RegisterViewModel::class.java)
//        bind.viewmodel=viewmodel
//
//        bind.registerButton.setOnClickListener(){
//            viewmodel.register()
//        }
//
//        viewmodel.mutable.observe(this ,  {
//            Log.d("regTest" , it.toString())
//            if(it.status=="ok"){
//                Log.d(TAG , "ثبت نام با موفقیت انجام شد ")
//                Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_token_key.toString(), it.token)
//                Repository.SharedPreferences.setSharedPreferences(this , R.string.preference_username_key.toString(), it.username)
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else if (it.status=="error"){
//                Log.d(TAG , "ثبت نام ناموفق. لطفا دوباره تلاش کنید")
//            }else if (it.status=="duplicate username"){
//                Log.d(TAG , "نام کاربری شما تکراری است. لطفا کمی آن را تغییر دهید.")
//            }
//        })

    }

    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}
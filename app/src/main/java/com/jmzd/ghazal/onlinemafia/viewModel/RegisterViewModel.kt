package com.jmzd.ghazal.onlinemafia.viewModel

import android.content.Context
import android.util.Patterns
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.dataModel.RegisterDataModel
import com.jmzd.ghazal.onlinemafia.repository.Api
import com.jmzd.ghazal.onlinemafia.repository.Repository
import com.jmzd.ghazal.onlinemafia.repository.SnackBarMaker
import io.reactivex.disposables.CompositeDisposable


class RegisterViewModel : ViewModel() {
    var username: String? = null
    var email: String? = null
    var password: String? = null
    var repeat: String? = null
    val mutable = MutableLiveData<RegisterDataModel>()
    val com = CompositeDisposable()

    fun register(view:View , context: Context) { // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        if (password == repeat && !password.isNullOrEmpty() && !username.isNullOrEmpty() && !email.isNullOrEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()) {
            Repository.CustomResponse.request(Api.invoke().registerApi(username!!,email!!, password!!), com) {
                mutable.value = it
            }
        } else if (password != repeat) {
          SnackBarMaker.SnackBar.setSnackBar(view ,context.getString(R.string.password_does_not_match_error))
        } else if (password.isNullOrEmpty() || username.isNullOrEmpty()||email.isNullOrEmpty()) {
            SnackBarMaker.SnackBar.setSnackBar(view , context.getString(R.string.empty_field_error))
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            SnackBarMaker.SnackBar.setSnackBar(view ,context.getString( R.string.email_format_error))
        }
    }

    fun login(view:View , context: Context){
        if (!password.isNullOrEmpty() && !username.isNullOrEmpty()) {
            Repository.CustomResponse.request(Api.invoke().loginApi(username!!, password!!), com) {
                mutable.value = it
            }
        } else if (password.isNullOrEmpty() || username.isNullOrEmpty()) {
            SnackBarMaker.SnackBar.setSnackBar(view ,context.getString(R.string.empty_field_error))
        }
    }

    override fun onCleared() {
        com.clear()
        super.onCleared()
    }
}





package com.jmzd.ghazal.onlinemafia.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmzd.ghazal.onlinemafia.dataModel.RegisterDataModel
import com.jmzd.ghazal.onlinemafia.repository.Api
import com.jmzd.ghazal.onlinemafia.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class RegisterViewModel : ViewModel() {
    var username: String? = null
    var password: String? = null
    var repeat: String? = null
    val mutable = MutableLiveData<RegisterDataModel>()
    val com = CompositeDisposable()

    fun register() { // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        if (password == repeat && !password.isNullOrEmpty() && !username.isNullOrEmpty()) {
            Repository.CustomResponse.request(Api.invoke().registerApi(username!!, password!!), com) {
                mutable.value = it
            }
        } else if (password != repeat) {
            Log.d("regLog", "تکرار پسورد مطابق نیست")
        } else if (password.isNullOrEmpty() || username.isNullOrEmpty()) {
            Log.d("regLog", "لطفا تمام فیلدها را پر کنید")
        }
    }

    override fun onCleared() {
        com.clear()
        super.onCleared()
    }
}





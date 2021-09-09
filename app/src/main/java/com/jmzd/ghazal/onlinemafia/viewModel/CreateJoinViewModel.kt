package com.jmzd.ghazal.onlinemafia.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmzd.ghazal.onlinemafia.dataModel.StatusDataModel
import com.jmzd.ghazal.onlinemafia.repository.Api
import com.jmzd.ghazal.onlinemafia.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class CreateJoinViewModel : ViewModel() {

    var tableName :String?=null
    var tablePass :String?=null
    var playerName :String?=null
    val mutable = MutableLiveData<StatusDataModel>()
    val Com = CompositeDisposable()
    fun createTable(){ // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        Repository.CustomResponse.request(Api.invoke().createTableApi(tableName!!,tablePass!!,playerName!!),Com){
            mutable.value=it
        }
    }

    fun joinTable(){ // از طریق دیتا بایندینگ یک ویو میگیریم
        Repository.CustomResponse.request(Api.invoke().joinTableApi(tableName!!,tablePass!!,playerName!!),Com){
            mutable.value=it
        }
    }

    override fun onCleared() {
        Com.clear()
        super.onCleared()
    }


}
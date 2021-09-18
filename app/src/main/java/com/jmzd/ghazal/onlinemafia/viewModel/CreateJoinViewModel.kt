package com.jmzd.ghazal.onlinemafia.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmzd.ghazal.onlinemafia.dataModel.*
import com.jmzd.ghazal.onlinemafia.repository.Api
import com.jmzd.ghazal.onlinemafia.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import java.lang.Error

class CreateJoinViewModel : ViewModel() {

    var tableName :String?=null
    var tablePass :String?=null
    private val contentType : String = "text/plain"
    var playerName :String?=null
    val mutable = MutableLiveData<StatusDataModel>()
  //  val mutableTest = MutableLiveData<KelidestanDataModel>()
    val com = CompositeDisposable()
    fun createTable(token:String){ // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        var auth:String = "Bearer $token"
        Log.d("test" , auth)
        Repository.CustomResponse.request(Api.invoke().createTableApi(auth , tableName!!,tablePass!!),com){
            mutable.value=it
        }
    }

    fun joinTable() { // از طریق دیتا بایندینگ یک ویو میگیریم
        Repository.CustomResponse.request(
            Api.invoke().joinTableApi(tableName!!, tablePass!!, playerName!!), com
        ) {
            mutable.value = it
        }
    }
        fun getList() {
            Repository.CustomResponse.request(Api.invoke().getListApi(), com) {
          //      mutableTest.value = it
            }
        }



    override fun onCleared() {
        com.clear()
        super.onCleared()
    }


}
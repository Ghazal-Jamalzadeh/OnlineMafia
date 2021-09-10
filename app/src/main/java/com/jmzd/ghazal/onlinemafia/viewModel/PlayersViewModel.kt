package com.jmzd.ghazal.onlinemafia.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmzd.ghazal.onlinemafia.dataModel.GetAdminDataModel
import com.jmzd.ghazal.onlinemafia.dataModel.PlayerDataModel
import com.jmzd.ghazal.onlinemafia.dataModel.PlayersCountDataModel
import com.jmzd.ghazal.onlinemafia.repository.Api
import com.jmzd.ghazal.onlinemafia.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class PlayersViewModel : ViewModel() {

    val mutable = MutableLiveData<List<PlayerDataModel>>()
    val countMutabele = MutableLiveData<PlayersCountDataModel>()
    val adminMutable = MutableLiveData<GetAdminDataModel>()

    val com = CompositeDisposable()
    fun getPlayers(tableName:String , tablePass:String){ // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        Repository.CustomResponse.request(Api.invoke().getPlayersApi(tableName,tablePass),com){
            mutable.value=it
        }
    }

    fun getCount(tableName:String , tablePass:String){ // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        Repository.CustomResponse.request(Api.invoke().getPlayersCountApi(tableName,tablePass),com){
            countMutabele.value=it
        }
    }

    fun getAdmin(tableName:String , tablePass:String){ // ااگر از طریق دیتا بایندینگ کال کنیم متد را به عنوان وروردی یک ویو می گیرد
        Repository.CustomResponse.request(Api.invoke().getAdmin(tableName,tablePass),com){
            adminMutable.value=it
        }
    }

    override fun onCleared() {
        com.clear()
        super.onCleared()
    }
}
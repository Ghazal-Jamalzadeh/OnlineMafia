package com.jmzd.ghazal.onlinemafia.repository

import com.jmzd.ghazal.onlinemafia.dataModel.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("kelidestan-json-1.html")
    fun getListApi(): Single<KelidestanDataModel> // چون آرایه است چیزی که برمیگردئ لیست می خواهیم. اگر فقط یک object{}  بود لازم نبود لیست باشد و فقط یک<DataModel_PostItem> کافی بود.

    @FormUrlEncoded
    @POST("createTable.php")
    fun createTableApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String, @Field("playerName")playerName:String): Single<StatusDataModel>

    @FormUrlEncoded
    @POST("joinTable.php")
    fun joinTableApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String, @Field("playerName")playerName:String): Single<StatusDataModel>

    @FormUrlEncoded
    @POST("getPlayers.php")
    fun getPlayersApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String): Single<List<PlayerDataModel>>

    @FormUrlEncoded
    @POST("deletePlayer.php")
    fun deletePlayerApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String, @Field("playerName")playerName:String): Single<StatusDataModel>

    @FormUrlEncoded
    @POST("playersCount.php")
    fun getPlayersCountApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String): Single<PlayersCountDataModel>

    @FormUrlEncoded
    @POST("getAdmin.php")
    fun getAdmin(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String): Single<GetAdminDataModel>

    @FormUrlEncoded
    @POST("deleteTable.php")
    fun deleteTableApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String): Single<StatusDataModel>



    companion object{
            operator fun invoke():Api{
                return Retrofit.Builder()
                    .baseUrl("https://www.kelidestan.com/fixed-url/")//http://192.168.1.104/Mafia/
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
            }
        }

    }

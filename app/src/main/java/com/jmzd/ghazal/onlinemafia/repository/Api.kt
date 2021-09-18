package com.jmzd.ghazal.onlinemafia.repository

import com.jmzd.ghazal.onlinemafia.dataModel.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    @GET("kelidestan-json-1.html")
    fun getListApi(): Single<KelidestanDataModel> // چون آرایه است چیزی که برمیگردئ لیست می خواهیم. اگر فقط یک object{}  بود لازم نبود لیست باشد و فقط یک<DataModel_PostItem> کافی بود.

    @FormUrlEncoded
    @POST("register.php")
    fun registerApi(@Field("username")username:String,  @Field("email")email:String, @Field("password")tablePass:String): Single<RegisterDataModel>

    @FormUrlEncoded
    @POST("login.php")
    fun loginApi(@Field("username")tableName:String, @Field("password")tablePass:String): Single<RegisterDataModel>

    @FormUrlEncoded
//    @Headers("Content-Type:application/json",
//    "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzE5NzQzNzcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzMjA2MDc3NywidXNlcm5hbWUiOiJzb2x0YW4ifQ.Mq-qSqAdLvSSDAQQPLyauelsKUwW9PvIB8MAgWjBSbI")
    @POST("createTable.php")
    fun createTableApi(@Header("Authorization") auth:String , @Field("tableName")tableName:String, @Field("tablePass")tablePass:String): Single<StatusDataModel>

//    @FormUrlEncoded
//    @POST("joinTable.php")
//    fun joinTableApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String, @Field("playerName")playerName:String): Single<StatusDataModel>

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
                 val client = OkHttpClient.Builder().apply {
                    addInterceptor(MyInterceptor())
                }.build()
                return Retrofit.Builder()
                    .baseUrl("http://192.168.1.104/Mafia-JWT/")//http://192.168.1.104/Mafia/    //https://www.kelidestan.com/fixed-url/
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
            }
        }

    }

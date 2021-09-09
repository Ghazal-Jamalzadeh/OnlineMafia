package com.jmzd.ghazal.onlinemafia.repository

import com.jmzd.ghazal.onlinemafia.dataModel.StatusDataModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("createTable.php")
    fun createTableApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String, @Field("playerName")playerName:String): Single<StatusDataModel>

    @FormUrlEncoded
    @POST("joinTable.php")
    fun joinTableApi(@Field("tableName")tableName:String, @Field("tablePass")tablePass:String, @Field("playerName")playerName:String): Single<StatusDataModel>


    companion object{ // در مثال java mvvm این قسمت را در webService پیاده سازی کردیم. این روش بهتری است و در آن از invoke استفاده شده است.
            operator fun invoke():Api{
                return Retrofit.Builder()
                    .baseUrl("http://192.168.43.194/Mafia/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
            }
        }

    }

//Kotlin's invoke operator
//An interesting feature of the Kotlin language is the ability to define an "invoke operator". When you specify an invoke operator on a class, it can be called on any instances of the class without a method name!
//This trick seems especially useful for classes that really only have one method to be used.
// more -> http://joshskeen.com/kotlins-invoke-operator/
//By using this, you can "call" your object as if it's a function.




// example :
//    @GET("login.php")
//    fun Getlogin(): Single<String>
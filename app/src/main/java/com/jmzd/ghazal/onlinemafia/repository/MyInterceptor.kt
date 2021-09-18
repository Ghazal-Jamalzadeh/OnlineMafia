package com.jmzd.ghazal.onlinemafia.repository

import com.bumptech.glide.RequestBuilder
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Content-Type" , "application/json")
            //.addHeader("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzE5NzQzNzcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzMjA2MDc3NywidXNlcm5hbWUiOiJzb2x0YW4ifQ.Mq-qSqAdLvSSDAQQPLyauelsKUwW9PvIB8MAgWjBSbI")
            .build()
        return chain.proceed(request)
    }
}
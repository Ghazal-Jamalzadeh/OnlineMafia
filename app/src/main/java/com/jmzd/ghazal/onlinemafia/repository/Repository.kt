package com.jmzd.ghazal.onlinemafia.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class Repository {
    object CustomResponse{
        //<T:Any> :
        // در اینجا any به این معنی است که چیزی که از آن سمت برای ما ارسال می شود مهم نیست و T هم به معنای generic است. بعضی از کلاس ها از نوع جنریک هستند. و چیزی که برای میفرستند را ما نمی دونیم چیه پس T در نظر میگیریم. برای مثال lis<String> یا می تواند از نوع integer یا یا DataModel  یا هر چیز دیگری باشد.
        fun <T:Any> request(api: Single<T>, com: CompositeDisposable, call:(T)->Unit){ //در واقع unit  به معنای نامعلوم است. چون مثلا نمی دانیم لیستی از دیتامدل ها را بر می گرداند یا مثلا استرینگ ها
            com.add(api // p15- java mvvm
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<T>(){ // داده ای هم که بر میگرداند از نوع T می باشد یعنی ممکن است هر چیزی باشد.
                    override fun onSuccess(t: T) {
                        call.invoke(t)
                    }

                    override fun onError(e: Throwable) {

                    }

                })
            )
        }
    }
}
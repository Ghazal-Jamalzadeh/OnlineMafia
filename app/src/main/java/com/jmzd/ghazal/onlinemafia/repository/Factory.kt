package com.jmzd.ghazal.onlinemafia.repository

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Factory (app: Application)  : ViewModelProvider.AndroidViewModelFactory(app){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}
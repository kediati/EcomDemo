package com.example.myapplication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.localstorage.sharedpref.SP_PASSWORD
import com.example.myapplication.localstorage.sharedpref.SP_USERNAME
import com.example.myapplication.localstorage.sharedpref.SharedPrefManager
import com.example.myapplication.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( val sharedPrefManager: SharedPrefManager) : ViewModel() {

    val navigationEventLiveData= MutableLiveData<Event<Unit>>()
    val navigationEvent = Event<Unit>(Unit)

    init {
        checkForLogin()
    }

    fun checkForLogin() {
        if(sharedPrefManager.checkIfPresent(SP_USERNAME) && sharedPrefManager.checkIfPresent(SP_PASSWORD)){
            navigationEventLiveData.value = navigationEvent
        }
    }

    fun onDataSubmit(username: String, password: String){
        if(!username.isNullOrBlank() && !password.isNullOrBlank()){
            sharedPrefManager.putString(SP_USERNAME, username)
            sharedPrefManager.putString(SP_PASSWORD, password)
            navigationEventLiveData.value  = navigationEvent
        }
    }
}
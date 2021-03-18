package com.mahmoudrefaie.sekkawahda.ui.Registeration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val username = MutableLiveData<String>()
    private val email = MutableLiveData<String>()
    private val password = MutableLiveData<String>()

    fun setData(username: String, email:String, password: String) {
        this.username.value = username
        this.email.value = email
        this.password.value = password
    }

    fun getUsername(): LiveData<String>? {
        return username
    }

    fun setEmail(email: String) {
        this.email.value = email
    }
    fun getEmail(): LiveData<String>? {
        return email
    }

    fun setPassword(password: String) {
        this.password.value = password
    }

    fun getPassword(): LiveData<String>? {
        return password
    }
}
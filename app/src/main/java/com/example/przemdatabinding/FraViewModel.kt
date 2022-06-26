package com.example.przemdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FraViewModel: ViewModel() {
    private val mutableLiveData = MutableLiveData<String>()

    fun setStringMLD(s: String){
        mutableLiveData.value = s
    }

    fun getStringMLD(): LiveData<String>{
        return mutableLiveData
    }

    private var slowa : String = ""

    fun setString(s: String){
        slowa = s
    }

    fun getString(): String{
        return slowa
    }
}
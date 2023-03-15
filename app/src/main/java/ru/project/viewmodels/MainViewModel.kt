package ru.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val toAllFragments: MutableLiveData<String> = MutableLiveData()

    fun changeAllFragmentsData() {
        toAllFragments.postValue("refresh")
    }

    fun getAllFragmentsData(): LiveData<String> {
        return toAllFragments
    }
}
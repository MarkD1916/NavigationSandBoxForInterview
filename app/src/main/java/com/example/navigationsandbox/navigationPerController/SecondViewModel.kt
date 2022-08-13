package com.example.navigationsandbox.navigationPerController

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationsandbox.Constants
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SecondViewModel: ViewModel() {
    var dataFromSecondActivity = ""

    override fun onCleared() {
        super.onCleared()
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onCleared")
    }
//    //-5.1, 6-
//    private val _dataFromSecondActivity: MutableStateFlow<String> = MutableStateFlow("")
//    val dataFromSecondActivity: StateFlow<String> = _dataFromSecondActivity
//
//    fun triggerStateFlow(){
//        _dataFromSecondActivity.value = "Data from second activity"
//    }

    //-5.2, 6-
//    private val _dataFromSecondActivity: MutableSharedFlow<String> = MutableStateFlow("")
//    val dataFromSecondActivity: SharedFlow<String> = _dataFromSecondActivity.asSharedFlow()
//
//    fun triggerSharedFlow(){
//        viewModelScope.launch {
//            _dataFromSecondActivity.emit("Data from second activity")
//        }
//    }
}
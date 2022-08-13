package com.example.navigationsandbox.navigationPerController

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.navigationsandbox.Constants
import com.example.navigationsandbox.Constants.CONFIRM_DATA_FROM_SECOND_ACTIVITY
import com.example.navigationsandbox.Constants.EXTRA_DATA
import com.example.navigationsandbox.Constants.REQUEST_CODE
import com.example.navigationsandbox.Constants.REQUEST_CODE_SECOND_ACTIVITY
import com.example.navigationsandbox.Constants.SAVE_INSTANCE_DATA
import com.example.navigationsandbox.R
import com.example.navigationsandbox.databinding.ActivityNavigationPerControllerFirstBinding
import com.example.navigationsandbox.databinding.ActivityNavigationPerControllerSecondBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

class NavigationPerControllerSecond : AppCompatActivity(), View.OnClickListener {
    //такой сценарий вполне подходит если у viewModel нет никаких зависимостей
    private val viewModel: SecondViewModel by lazy {
        ViewModelProvider(this)[SecondViewModel::class.java]
    }

    private var _binding: ActivityNavigationPerControllerSecondBinding? = null
    val mBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onCreate")
        val extraInfo = intent.getStringExtra(EXTRA_DATA)
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onCreate: $extraInfo") //3 сценарий
        _binding = ActivityNavigationPerControllerSecondBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnReturnResultToSecondActivity.setOnClickListener(this)
        //-5.1, 5.2, 5.3, 6-
//        subscribeToObservables()
        //-5.4-
        val dataFromSecondActivity = savedInstanceState?.getString(SAVE_INSTANCE_DATA) ?: ""
        triggerData(dataFromSecondActivity)
    }

    override fun onStart() {
        super.onStart()
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Constants.TAG_SECOND_ACTIVITY, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(Constants.TAG_SECOND_ACTIVITY, "onDestroy")
    }

    //-5.4-
    fun triggerData(data: String) {
        viewModel.dataFromSecondActivity = data
        if (data.isNotEmpty()) {
            val dataIntent = Intent().apply {
                putExtra(
                    CONFIRM_DATA_FROM_SECOND_ACTIVITY,
                    viewModel.dataFromSecondActivity
                )
                putExtra(REQUEST_CODE, REQUEST_CODE_SECOND_ACTIVITY)
            }
            setResult(Activity.RESULT_OK, dataIntent)
        }

    }
    //-5.1-
//    fun subscribeToObservables() {
//        lifecycleScope.launchWhenStarted {
//            Log.d(Constants.TAG_SECOND_ACTIVITY, "Trigger observer")
//            viewModel.dataFromSecondActivity
//                .filter {
//                    it.isNotEmpty()
//                }
//                .collectLatest {
//                    val data = Intent().apply {
//                        putExtra(
//                            CONFIRM_DATA_FROM_SECOND_ACTIVITY,
//                            viewModel.dataFromSecondActivity.value
//                        )
//                        putExtra(REQUEST_CODE, REQUEST_CODE_SECOND_ACTIVITY)
//                    }
//                    setResult(Activity.RESULT_OK, data)
//            }
//        }
//        //-6-
//        lifecycleScope.launchWhenStarted {
//            viewModel.dataFromSecondActivity.collectLatest {
//                mBinding.dataCollectStatus.text = it.ifEmpty { "User don't click" }
//            }
//        }
//
//    }
    //-5.2-
//    fun subscribeToObservables() {
//        lifecycleScope.launchWhenStarted {
//            Log.d(Constants.TAG_SECOND_ACTIVITY, "Trigger observer")
//
//            viewModel.dataFromSecondActivity.filter {
//                it != ""
//            }
//                .collectLatest {
//                    val data = Intent().apply {
//                        putExtra(
//                            CONFIRM_DATA_FROM_SECOND_ACTIVITY,
//                            it
//                        )
//                        putExtra(REQUEST_CODE, REQUEST_CODE_SECOND_ACTIVITY)
//                    }
//                    setResult(Activity.RESULT_OK, data)
//                }
//        }
    //-6-
//        lifecycleScope.launchWhenStarted {
//            viewModel.dataFromSecondActivity.collectLatest {
//                mBinding.dataCollectStatus.text = it.ifEmpty { "User don't click" }
//            }
//        }
//    }

    //-4, 5.1, - сценарий
    override fun onClick(v: View) {
        Log.d(Constants.TAG_FIRST_ACTIVITY, "Click")
        when (v.id) {
            R.id.btnReturnResultToSecondActivity -> {
                //-5.4-
                triggerData("Data from second activity")
                //-5.2, 6-
//                viewModel.triggerSharedFlow()
                //-5.1, 6-
//                viewModel.triggerStateFlow()
                //-4- сценарий
//                val data = Intent().apply {
//                    putExtra(CONFIRM_DATA_FROM_SECOND_ACTIVITY, "Data from second activity")
//                    putExtra(REQUEST_CODE, REQUEST_CODE_SECOND_ACTIVITY)
//                }
//                setResult(Activity.RESULT_OK, data)
            }
        }
    }
    //-5.4-
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_INSTANCE_DATA, viewModel.dataFromSecondActivity)
    }
}
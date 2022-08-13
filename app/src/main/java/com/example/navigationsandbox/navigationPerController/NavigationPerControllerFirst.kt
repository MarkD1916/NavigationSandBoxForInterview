package com.example.navigationsandbox.navigationPerController

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.navigationsandbox.Constants
import com.example.navigationsandbox.Constants.CONFIRM_DATA_FROM_SECOND_ACTIVITY
import com.example.navigationsandbox.Constants.EXTRA_DATA
import com.example.navigationsandbox.Constants.REQUEST_CODE
import com.example.navigationsandbox.Constants.REQUEST_CODE_SECOND_ACTIVITY
import com.example.navigationsandbox.Constants.TAG_FIRST_ACTIVITY
import com.example.navigationsandbox.R
import com.example.navigationsandbox.databinding.ActivityNavigationPerControllerFirstBinding

class NavigationPerControllerFirst : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityNavigationPerControllerFirstBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG_FIRST_ACTIVITY, "onCreate")
        super.onCreate(savedInstanceState)
        _binding = ActivityNavigationPerControllerFirstBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnNavigateToSecondActivity.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_FIRST_ACTIVITY, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_FIRST_ACTIVITY, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_FIRST_ACTIVITY, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_FIRST_ACTIVITY, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.d(TAG_FIRST_ACTIVITY, "onDestroy")
    }

    override fun onClick(v: View) {
        Log.d(TAG_FIRST_ACTIVITY, "Click")
        when (v.id) {
            R.id.btnNavigateToSecondActivity -> {
                val intent = Intent(this, NavigationPerControllerSecond::class.java)
                intent.putExtra(EXTRA_DATA, "ExtraData From First Activity") //3 сценарий
                //startActivity(intent) //-1- сценарий
                //finish()//-2- сценарий
                getResult.launch(intent) //-4- сценарий
            }
        }
    }

    //-4- сценарий
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode != Activity.RESULT_OK) {
                return@registerForActivityResult
            } else {
                when (it.data?.getIntExtra(REQUEST_CODE, -1)) {
                    REQUEST_CODE_SECOND_ACTIVITY -> {
                        val value = it.data?.getStringExtra(CONFIRM_DATA_FROM_SECOND_ACTIVITY)
                        Log.d(TAG_FIRST_ACTIVITY, value.toString())
                    }
                }

            }
        }

}
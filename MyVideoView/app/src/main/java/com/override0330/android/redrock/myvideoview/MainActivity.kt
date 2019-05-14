package com.override0330.android.redrock.myvideoview

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var myMediaManager: MyMediaManager
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myMediaManager.init()
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
                //横屏
                myMediaManager.changeToFullScreen()

            }else{
                //竖屏
                myMediaManager.changeToSmallScreen()
            }
        }
    }
}

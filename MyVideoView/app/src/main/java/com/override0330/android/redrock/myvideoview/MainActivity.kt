package com.override0330.android.redrock.myvideoview

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var videoTitle : TextView
    private lateinit var videoControl : ImageView
    private lateinit var videoSchedule : SeekBar
    private lateinit var videoFull : ImageView
    private lateinit var surfaceView: SurfaceView
    private lateinit var myMediaManager: MyMediaManager
    private lateinit var parentView: LinearLayout
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        surfaceView = findViewById(R.id.sv_video)
        videoTitle = findViewById(R.id.tv_title)
        videoSchedule = findViewById(R.id.sb_play_control)
        videoFull = findViewById(R.id.iv_screen)
        videoControl = findViewById(R.id.iv_play)
        parentView = findViewById(R.id.ll_video_Collection)
        myMediaManager = MyMediaManager.Builder(this,surfaceView,parentView)
                .setControlButton(videoControl)
                .setControlScreen(videoFull)
                .setControlSeekBar(videoSchedule)
                .setTitle(videoTitle).build()
        myMediaManager.controlBar = findViewById(R.id.ll_control_bar)
        myMediaManager.detailBar = findViewById(R.id.ll_title)
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

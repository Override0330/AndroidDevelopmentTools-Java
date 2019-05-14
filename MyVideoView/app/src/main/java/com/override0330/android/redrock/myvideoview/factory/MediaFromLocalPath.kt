package com.override0330.android.redrock.myvideoview.factory

import android.app.Activity
import com.override0330.android.redrock.myvideoview.MyMediaManager
import com.override0330.android.redrock.myvideoview.preparemethod.MediaPrepareFromLocalPath

class MediaFromLocalPath (private val myMediaManager: MyMediaManager,
                          private val localPath: String,
                          private val activity: Activity){
    fun build():MyMediaManager{
        val mediaPrepareFromLocalPath = MediaPrepareFromLocalPath(myMediaManager,localPath,activity)
        myMediaManager.setPrepareMethod(mediaPrepareFromLocalPath)
        return  myMediaManager
    }

}
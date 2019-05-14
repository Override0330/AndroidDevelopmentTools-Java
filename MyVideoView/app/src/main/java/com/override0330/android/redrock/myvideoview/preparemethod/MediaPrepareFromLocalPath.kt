package com.override0330.android.redrock.myvideoview.preparemethod

import android.app.Activity
import android.media.MediaPlayer
import android.net.Uri
import com.override0330.android.redrock.myvideoview.`interface`.MediaPrepare
//实现了从本地加载
class MediaPrepareFromLocalPath(private val mediaPlayer: MediaPlayer,
                                private val localPath:String,
                                private val activity: Activity) : MediaPrepare {

    override fun prepare() {
        mediaPlayer.setDataSource(activity, Uri.parse(localPath))
        mediaPlayer.prepare()
    }

}
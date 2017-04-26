package com.mlshv.dayree.util

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import java.io.IOException

class AudioRecorder {
    private val LOG_TAG = "AudioRecorder"
    private var mRecorder: MediaRecorder? = null
    private var mPlayer: MediaPlayer? = null
    private val samplingRate = 44100
    private val bitRate = 48000
    private val outputFormat = MediaRecorder.OutputFormat.MPEG_4
    private val encoder = MediaRecorder.AudioEncoder.AAC

    fun startRecordingToFile(outputFileName: String) {
        mRecorder = MediaRecorder()
        with(mRecorder!!) {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setAudioSamplingRate(samplingRate)
            setAudioEncodingBitRate(bitRate)
            setOutputFormat(outputFormat)
            setAudioEncoder(encoder)
            setOutputFile(outputFileName)
            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
            start()
        }
    }

    fun stopRecording() {
        mRecorder?.stop()
        mRecorder?.release()
        mRecorder = null
    }

    fun release() {
        if (mRecorder != null) {
            mRecorder?.release()
            mRecorder = null
        }
        if (mPlayer != null) {
            mPlayer?.release()
            mPlayer = null
        }
    }
}
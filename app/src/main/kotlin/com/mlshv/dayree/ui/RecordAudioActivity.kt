package com.mlshv.dayree.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import com.mlshv.dayree.R
import com.mlshv.dayree.presenter.AudioPresenter
import com.mlshv.dayree.util.PermissionsHelper


class RecordAudioActivity : ReeActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_audio)

        // Toolbar
        initToolbar()

        // Permissions
        PermissionsHelper.verifyStoragePermissions(this)
        PermissionsHelper.verifyAudioRecordPermissions(this)

        val presenter = AudioPresenter(this)

        // Listeners
        val startRecordButton = findViewById(R.id.start_record_button) as Button
        startRecordButton.setOnClickListener {
            presenter.startRecording()
        }

        val stopRecordButton = findViewById(R.id.stop_record_button) as Button
        var recordId = 0L
        stopRecordButton.setOnClickListener {
            recordId = presenter.stopRecordingAndGetRecordId()
        }

        val playRecordButton = findViewById(R.id.play_record_button) as Button
        playRecordButton.setOnClickListener {
            presenter.playAudio(recordId)
        }
    }

    private fun initToolbar() {
        this.title = "Record new audio" // TODO move to resource
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) finish()
        }
    }
}
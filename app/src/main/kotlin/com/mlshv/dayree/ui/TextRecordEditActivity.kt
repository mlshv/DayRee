package com.mlshv.dayree.ui

import android.Manifest
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.mlshv.dayree.R
import com.mlshv.dayree.util.DatabaseHelper
import com.mlshv.dayree.model.Record
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.content.pm.PackageManager
import android.support.annotation.NonNull
import android.Manifest.permission
import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import java.io.IOException
import android.view.ViewGroup
import android.widget.LinearLayout
import android.support.v4.app.ActivityCompat




class TextRecordEditActivity : ReeActivity() {
    lateinit var titleEditText : EditText
    lateinit var noteEditText : EditText

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_record_edit)
        initToolbar()
        titleEditText = findViewById(R.id.title_edit_text) as EditText
        noteEditText = findViewById(R.id.note_edit_text) as EditText
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_save -> {
                val titleInput = titleEditText.text.toString()
                val textInput = noteEditText.text.toString()
                if (titleInput.isBlank() && textInput.isBlank()) return true
                val newRecord = Record(
                        title = titleInput,
                        text = textInput)
                DatabaseHelper.putRecord(newRecord)
                this.finish()
                return true
            }
            R.id.action_settings -> return true
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.record_activity_menu, menu)
        return true
    }

    private fun initToolbar() {
        this.title = "Add record" // TODO move to resource
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }
}
package com.mlshv.dayree.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

class PermissionsHelper {
    companion object {
        // Storage Permissions
        private val REQUEST_EXTERNAL_STORAGE_PERMISSION = 1
        private val storagePermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        // Audio Permissions
        private val REQUEST_RECORD_AUDIO_PERMISSION = 200
        private var audioPermissions = arrayOf(Manifest.permission.RECORD_AUDIO)

        /**
         * Checks if the app has permission to write to device storage
         * If the app does not has permission then the user will be prompted to grant permissions
         * @param activity
         */
        fun verifyStoragePermissions(activity: Activity) {
            // Check if we have write permission
            val permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        storagePermissions,
                        REQUEST_EXTERNAL_STORAGE_PERMISSION
                )
            }
        }

        /**
         * Checks if the app has permission to record audio
         * If the app does not has permission then the user will be prompted to grant permissions
         * @param activity
         */
        fun verifyAudioRecordPermissions(activity: Activity) {
            // Check if we have write permission
            val permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        audioPermissions,
                        REQUEST_RECORD_AUDIO_PERMISSION
                )
            }
        }
    }
}
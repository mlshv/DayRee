package com.mlshv.dayree.presenter

import android.media.MediaPlayer
import com.mlshv.dayree.DayReeApplication
import com.mlshv.dayree.model.Audio
import com.mlshv.dayree.ui.ReeActivity
import com.mlshv.dayree.util.*
import java.io.File
import java.io.File.separator
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class AudioPresenter(val activity: ReeActivity) {
    val recorder = AudioRecorder()
    val cryptoHelper = CryptoHelper(DayReeApplication.password, DayReeApplication.getSalt())
    var temporaryFileName: String = UUID.randomUUID().toString() + ".mp4"

    /**
     * Starts the recording. Data recorded in a randomly named temporary file
     */
    fun startRecording() {
        temporaryFileName = UUID.randomUUID().toString() + ".mp4"
        val outputLocation = activity.externalCacheDir!!.absolutePath + separator + temporaryFileName
        recorder.startRecordingToFile(outputLocation)
        File(outputLocation).deleteOnExit()
    }

    /**
     * Stops recording and returns new record's id in the database
     * @return record's id in the database
     */
    fun stopRecordingAndGetRecordId(): Long {
        recorder.stopRecording()
        val tempFileLocation = activity.externalCacheDir!!.absolutePath + separator + temporaryFileName
        val encryptedData = cryptoHelper.encrypt(readFile(tempFileLocation))
        val encryptedFileName = UUID.randomUUID().toString()
        writePrivateFile(activity, encryptedFileName, encryptedData)
        return DatabaseHelper.putAudio(Audio(
                title = "Test",
                filename = encryptedFileName))
    }

    /**
     * Plays audio file by given database row id
     * @param id row id
     */
    fun playAudio(id: Long) {
        val audio = DatabaseHelper.getAudio(id)
        val encryptedData = readPrivateFile(activity, audio.filename)
        val audioData = cryptoHelper.decrypt(encryptedData)
        playByteArrayAudio(audioData)
    }

    /**
     * Plays MP4 AAC audio given as byte array
     * Creates temporary file as a buffer for MediaPlayer
     * @param soundByteArray audio data to play
     */
    private fun playByteArrayAudio(soundByteArray: ByteArray) {
        try {
            val tempFile = File.createTempFile("tmp", "mp4", activity.externalCacheDir!!.absoluteFile)
            tempFile.deleteOnExit()
            val fos = FileOutputStream(tempFile)
            fos.write(soundByteArray)
            fos.close()
            val mediaPlayer = MediaPlayer()
            val fis = FileInputStream(tempFile)
            mediaPlayer.setDataSource(fis.fd)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (ex : IOException) {
            ex.printStackTrace()
        }
    }
}
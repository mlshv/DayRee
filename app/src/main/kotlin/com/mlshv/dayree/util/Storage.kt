package com.mlshv.dayree.util

import android.content.Context
import android.util.Log
import com.mlshv.dayree.DayReeApplication
import java.io.*

fun writePrivateFile(ctx: Context, fileName: String, bytesToSave: ByteArray) {
    var outputStream: BufferedOutputStream? = null
    try {
        outputStream = BufferedOutputStream(ctx.openFileOutput(fileName, Context.MODE_PRIVATE))
        with(outputStream) {
            write(bytesToSave)
            flush()
            close()
        }
        Log.d(DayReeApplication.reeLogTag, "Wrote ${bytesToSave.size} bytes to " + fileName)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        outputStream?.close()
    }
}

fun readPrivateFile(ctx: Context, fileName: String): ByteArray {
    var inputStream: BufferedInputStream? = null
    var contents = ByteArray(0)
    try {
        val fileInputStream = ctx.openFileInput(fileName)
        contents = ByteArray(fileInputStream.available())
        inputStream = BufferedInputStream(fileInputStream)
        inputStream.read(contents)
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return contents
}

fun readFile(fileLocation: String): ByteArray {
    val file = File(fileLocation)
    val contents = ByteArray(file.length().toInt())
    var inputStream: BufferedInputStream? = null
    try {
        inputStream = BufferedInputStream(FileInputStream(file))
        inputStream.read(contents)
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return contents
}
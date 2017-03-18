package dayree

import android.app.Application
import android.content.Context
import dayree.db.DatabaseHelper
import net.sqlcipher.database.SQLiteDatabase

class DayReeApplication : Application() {
    companion object {
        private val lockStatus = "LOCK_STATUS"

        private lateinit var singleton: DayReeApplication

        fun getInstance(): DayReeApplication {
            return singleton
        }

        fun setLocked(lock: Boolean) {
            singleton.getSharedPreferences(lockStatus, Context.MODE_PRIVATE)!!.edit().putBoolean("LOCK", lock).apply()
        }

        fun isLocked() = singleton.getSharedPreferences(lockStatus, Context.MODE_PRIVATE)!!.getBoolean("LOCK", true)
    }

    private val dbHelper = DatabaseHelper(this)

    override fun onCreate() {
        super.onCreate()
        singleton = this
        // SQLCipher initialization
        SQLiteDatabase.loadLibs(this)
    }

    fun isDatabaseExists() = dbHelper.isDatabaseExists()

    fun isPasswordCorrect(password: String) = dbHelper.tryOpenDatabaseWithPassword(password)

    fun createDatabaseWithPassword(password: String) = dbHelper.getWritableDatabase(password)!!

}
package dayree.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.mlshv.dayree.R
import dayree.db.DatabaseHelper
import dayree.model.Record

class RecordActivity : ReeActivity() {
    var titleEditText : EditText? = null
    var noteEditText : EditText? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        toolbarInit()
        titleEditText = findViewById(R.id.title_edit_text) as EditText
        noteEditText = findViewById(R.id.note_edit_text) as EditText
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_save -> {
                val titleInput = titleEditText!!.text.toString()
                val textInput = noteEditText!!.text.toString()
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

    private fun toolbarInit() {
        this.title = "Add record" // TODO move to resource
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }
}
package com.mlshv.dayree.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mlshv.dayree.R
import android.content.Intent
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.mlshv.dayree.DayReeApplication
import com.mlshv.dayree.util.DatabaseHelper


class LoginActivity : AppCompatActivity() {
    val app: DayReeApplication = DayReeApplication.getInstance()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (findViewById(R.id.test_pin) as Button).setOnClickListener { testPin() }

        val passwordEditText = findViewById(R.id.password_text) as EditText
        val repeatPasswordEditText = findViewById(R.id.repeat_password_text) as EditText
        val loginButton = findViewById(R.id.login_button) as Button

        if (app.isDatabaseExists()) {
            loginButton.setText(R.string.log_in)
            (repeatPasswordEditText.parent as RelativeLayout).removeView(repeatPasswordEditText)
            val layoutParams = RelativeLayout.LayoutParams(loginButton.layoutParams)
            layoutParams.addRule(RelativeLayout.BELOW, R.id.password_text)
            layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT
            loginButton.layoutParams = layoutParams
        }

        loginButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val passwordsMatch = password == repeatPasswordEditText.text.toString()
            DayReeApplication.password = password

            if (app.isDatabaseExists()) {
                if (app.isPasswordCorrect(password)) {
                    useApplication()
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (passwordsMatch) {
                    app.createDatabaseWithPassword(password)
                    app.isPasswordCorrect(password)
                    useApplication()
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        DatabaseHelper.closeDatabase()
        super.onResume()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

    fun useApplication() {
        DayReeApplication.setLocked(false)
        if (intent.action != null) {
            val mainIntent = Intent(this, MainActivity::class.java)
            this.startActivity(mainIntent)
        }
        this.finish()
    }

    fun testPin() {
        val intetiwe = Intent(this, PinActivity::class.java)
        this.startActivity(intetiwe)
    }
}

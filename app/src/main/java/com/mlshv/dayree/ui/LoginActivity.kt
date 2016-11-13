package com.mlshv.dayree.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mlshv.dayree.R
import android.content.Intent
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.mlshv.dayree.DayReeApplication


class LoginActivity : AppCompatActivity() {
    val app: DayReeApplication = DayReeApplication.getInstance()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val passwordEditText = findViewById(R.id.password_text) as EditText
        val repeatPasswordEditText = findViewById(R.id.repeat_password_text) as EditText
        val loginButton = findViewById(R.id.login_button) as Button

        if (app.isDatabaseExists()) {
            loginButton.setText(R.string.log_in)
            (repeatPasswordEditText.parent as RelativeLayout).removeView(repeatPasswordEditText)
            val layoutParams = RelativeLayout.LayoutParams(loginButton.layoutParams)
            layoutParams.addRule(RelativeLayout.BELOW, R.id.password_text)
            loginButton.layoutParams = layoutParams
        }

        loginButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val passwordsMatch = password == repeatPasswordEditText.text.toString()

            if (app.isDatabaseExists()) {
                if (app.isPasswordCorrect(password)) {
                    goToMainActivity()
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (passwordsMatch) {
                    app.createDatabaseWithPassword(password)
                    goToMainActivity()
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun goToMainActivity() {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
        this.finish()
    }
}
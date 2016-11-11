package com.mlshv.dayree.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.mlshv.dayree.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById(R.id.login_button) as Button
        val passwordTextView = findViewById(R.id.password_text) as TextView
    }
}

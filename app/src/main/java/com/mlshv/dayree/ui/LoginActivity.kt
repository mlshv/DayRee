package com.mlshv.dayree.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mlshv.dayree.R
import android.content.Intent
import android.widget.EditText
import android.widget.Toast



class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById(R.id.login_button) as Button
        val passwordTextView = findViewById(R.id.password_text) as EditText

        loginButton.setOnClickListener {
            if (passwordTextView.text.toString() == "hardcoded") {
                val myIntent = Intent(this, MainActivity::class.java)
                this.startActivity(myIntent)
            } else {
                val toast = Toast.makeText(applicationContext,
                        passwordTextView.text, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}

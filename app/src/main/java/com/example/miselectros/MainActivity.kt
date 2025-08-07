package com.example.miselectros

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailLayout = findViewById(R.id.login_email_layout)
        passwordLayout = findViewById(R.id.login_password_layout)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            if (validateInputs()) {
                // Proceed with login logic
            }
        }

        findViewById<TextView>(R.id.create_account).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        findViewById<TextView>(R.id.forgot_password).setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }

    private fun validateInputs(): Boolean {
        var valid = true

        val email = emailInput.text?.toString()?.trim().orEmpty()
        val password = passwordInput.text?.toString().orEmpty()

        if (email.isEmpty()) {
            emailLayout.error = getString(R.string.error_email_required)
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = getString(R.string.error_invalid_email)
            valid = false
        } else {
            emailLayout.error = null
        }

        if (password.isEmpty()) {
            passwordLayout.error = getString(R.string.error_password_required)
            valid = false
        } else if (!isValidPassword(password)) {
            passwordLayout.error = getString(R.string.error_invalid_password)
            valid = false
        } else {
            passwordLayout.error = null
        }

        return valid
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}")
        return regex.matches(password)
    }
}

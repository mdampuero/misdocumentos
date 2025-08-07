package com.example.miselectros

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var fullNameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout

    private lateinit var fullNameInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var confirmPasswordInput: TextInputEditText
    private lateinit var registerButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar: MaterialToolbar = findViewById(R.id.register_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fullNameLayout = findViewById(R.id.full_name_layout)
        emailLayout = findViewById(R.id.register_email_layout)
        passwordLayout = findViewById(R.id.register_password_layout)
        confirmPasswordLayout = findViewById(R.id.confirm_password_layout)

        fullNameInput = findViewById(R.id.full_name_input)
        emailInput = findViewById(R.id.register_email_input)
        passwordInput = findViewById(R.id.register_password_input)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)
        registerButton = findViewById(R.id.register_button)

        registerButton.setOnClickListener {
            if (validateInputs()) {
                // Proceed with account creation logic
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun validateInputs(): Boolean {
        var valid = true

        val fullName = fullNameInput.text?.toString()?.trim().orEmpty()
        val email = emailInput.text?.toString()?.trim().orEmpty()
        val password = passwordInput.text?.toString().orEmpty()
        val confirmPassword = confirmPasswordInput.text?.toString().orEmpty()

        if (fullName.isEmpty()) {
            fullNameLayout.error = getString(R.string.error_full_name)
            valid = false
        } else {
            fullNameLayout.error = null
        }

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

        if (confirmPassword.isEmpty()) {
            confirmPasswordLayout.error = getString(R.string.error_confirm_password_required)
            valid = false
        } else if (password != confirmPassword) {
            confirmPasswordLayout.error = getString(R.string.error_passwords_not_match)
            valid = false
        } else {
            confirmPasswordLayout.error = null
        }

        return valid
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}")
        return regex.matches(password)
    }
}

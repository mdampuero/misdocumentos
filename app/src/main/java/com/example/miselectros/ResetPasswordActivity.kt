package com.example.miselectros

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var emailLayout: TextInputLayout
    private lateinit var emailInput: TextInputEditText
    private lateinit var resetButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val toolbar: MaterialToolbar = findViewById(R.id.reset_password_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        emailLayout = findViewById(R.id.reset_email_layout)
        emailInput = findViewById(R.id.reset_email_input)
        resetButton = findViewById(R.id.reset_password_button)

        resetButton.setOnClickListener {
            if (validateEmail()) {
                // TODO: trigger backend to send code
                startActivity(Intent(this, CodeVerificationActivity::class.java))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun validateEmail(): Boolean {
        val email = emailInput.text?.toString()?.trim().orEmpty()
        return if (email.isEmpty()) {
            emailLayout.error = getString(R.string.error_email_required)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = getString(R.string.error_invalid_email)
            false
        } else {
            emailLayout.error = null
            true
        }
    }
}

package com.example.miselectros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CodeVerificationActivity : AppCompatActivity() {

    private lateinit var codeLayout: TextInputLayout
    private lateinit var codeInput: TextInputEditText
    private lateinit var validateButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)

        val toolbar: MaterialToolbar = findViewById(R.id.code_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        codeLayout = findViewById(R.id.code_layout)
        codeInput = findViewById(R.id.code_input)
        validateButton = findViewById(R.id.validate_code_button)

        validateButton.setOnClickListener {
            if (validateCode()) {
                // TODO: implement code validation
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun validateCode(): Boolean {
        val code = codeInput.text?.toString()?.trim().orEmpty()
        return if (!code.matches(Regex("^\\d{6}$"))) {
            codeLayout.error = getString(R.string.error_invalid_code)
            false
        } else {
            codeLayout.error = null
            true
        }
    }
}

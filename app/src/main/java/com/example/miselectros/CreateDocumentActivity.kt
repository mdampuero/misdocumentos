package com.example.miselectros

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CreateDocumentActivity : AppCompatActivity() {

    private val types = listOf("Factura", "Recibo", "Otro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showStep1()
    }

    private fun showStep1() {
        setContentView(R.layout.activity_document_step1)
        val nameField: EditText = findViewById(R.id.edit_name)
        val typeSpinner: Spinner = findViewById(R.id.spinner_type)
        val dateField: EditText = findViewById(R.id.edit_date)
        val storeField: EditText = findViewById(R.id.edit_store)
        val descriptionField: EditText = findViewById(R.id.edit_description)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.adapter = adapter

        intent.getStringExtra("name")?.let { nameField.setText(it) }
        intent.getStringExtra("type")?.let { t ->
            val pos = types.indexOf(t)
            if (pos >= 0) typeSpinner.setSelection(pos)
        }

        findViewById<Button>(R.id.button_next).setOnClickListener {
            val name = nameField.text.toString().trim()
            if (name.isEmpty()) {
                nameField.error = getString(R.string.required)
                return@setOnClickListener
            }
            showStep2()
        }
    }

    private fun showStep2() {
        setContentView(R.layout.activity_document_step2)
        findViewById<Button>(R.id.button_camera).setOnClickListener {
            Toast.makeText(this, R.string.camera_action, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button_file).setOnClickListener {
            Toast.makeText(this, R.string.choose_file_action, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button_save).setOnClickListener {
            Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

package com.example.miselectros

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class DocumentsActivity : AppCompatActivity(), DocumentAdapter.DocumentActionListener {

    private lateinit var documents: MutableList<Document>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        listView = findViewById(R.id.documents_list_view)
        documents = loadDocuments()
        listView.adapter = DocumentAdapter(this, documents, this)

        findViewById<FloatingActionButton>(R.id.fab_add_document).setOnClickListener {
            startActivity(Intent(this, CreateDocumentActivity::class.java))
        }
    }

    private fun loadDocuments(): MutableList<Document> {
        val mockJson = """
            [
              {"id":1,"name":"Factura de luz","type":"Factura"},
              {"id":2,"name":"Recibo de agua","type":"Recibo"}
            ]
        """
        val arr = JSONArray(mockJson)
        val list = mutableListOf<Document>()
        for (i in 0 until arr.length()) {
            val obj = arr.getJSONObject(i)
            list.add(Document(obj.getInt("id"), obj.getString("name"), obj.getString("type")))
        }
        return list
    }

    override fun onEdit(document: Document) {
        val intent = Intent(this, CreateDocumentActivity::class.java)
        intent.putExtra("name", document.name)
        intent.putExtra("type", document.type)
        startActivity(intent)
    }

    override fun onDelete(document: Document) {
        AlertDialog.Builder(this)
            .setTitle(R.string.title_documents)
            .setMessage(R.string.delete_confirmation)
            .setPositiveButton(R.string.yes) { _, _ ->
                documents.remove(document)
                (listView.adapter as DocumentAdapter).notifyDataSetChanged()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }
}

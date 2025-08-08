package com.example.miselectros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class DocumentAdapter(
    private val context: Context,
    private val documents: MutableList<Document>,
    private val listener: DocumentActionListener
) : BaseAdapter() {

    interface DocumentActionListener {
        fun onEdit(document: Document)
        fun onDelete(document: Document)
    }

    override fun getCount(): Int = documents.size

    override fun getItem(position: Int): Document = documents[position]

    override fun getItemId(position: Int): Long = documents[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_document, parent, false)
        val document = getItem(position)
        val name: TextView = view.findViewById(R.id.text_name)
        val type: TextView = view.findViewById(R.id.text_type)
        val editButton: ImageButton = view.findViewById(R.id.btn_edit)
        val deleteButton: ImageButton = view.findViewById(R.id.btn_delete)

        name.text = document.name
        type.text = document.type

        editButton.setOnClickListener { listener.onEdit(document) }
        deleteButton.setOnClickListener { listener.onDelete(document) }

        return view
    }
}

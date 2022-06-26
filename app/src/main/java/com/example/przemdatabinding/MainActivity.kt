package com.example.przemdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private val viewModel : FraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val file: File = File(applicationContext.getExternalFilesDir(null), "labyrinth.txt")
        fun wpiszTekst() {
            file.writeText("Przem_K\n", Charsets.UTF_8)
            file.appendText("Korecki", Charsets.UTF_8)
        }
        wpiszTekst()
        val readResult = FileInputStream(file).bufferedReader().use { it.readText() }
        viewModel.setString(readResult)

        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = viewModel.getString()

        supportFragmentManager
            .beginTransaction().apply {
                add(R.id.fragment_container, ScrollingFragment())
                    .commit()
            }
            
        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.setStringMLD(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        viewModel.getStringMLD().observe(this){
            t -> textView.text = t
        }
    }
}
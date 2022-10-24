package com.moviles.practicadibujo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.moviles.practicadibujo.R
import com.moviles.practicadibujo.ui.components.MyCanvas

class MainActivity : AppCompatActivity() {
    lateinit var mylienzo: MyCanvas
    lateinit var btncuadrado: Button
    lateinit var btnlinea: Button
    lateinit var btncirculo: Button
    lateinit var btnPaint: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mylienzo = findViewById(R.id.myCanvas)
        btncuadrado = findViewById(R.id.btncuadrado)
        btncirculo = findViewById(R.id.btncirculo)
        btnlinea = findViewById(R.id.btnlinea)
        btnPaint = findViewById(R.id.paint)
        setupListeners()
    }

    private fun setupListeners() {
        btncirculo.setOnClickListener { mylienzo.form("circulo") }
        btncuadrado.setOnClickListener { mylienzo.form("cuadrado") }
        btnlinea.setOnClickListener { mylienzo.form("linea") }
        btnPaint.setOnClickListener {
            val intent = Intent(this, PaintActivity::class.java)
            startActivity(intent)
        }
    }
}
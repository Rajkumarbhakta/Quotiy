package com.rkbapps.quotiy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var txtQuote: TextView
    lateinit var txtAuthor: TextView
    lateinit var txtNext: TextView
    lateinit var txtPrevious: TextView
    lateinit var imgShare: ImageView

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]

        txtAuthor = findViewById(R.id.txtAuthor)
        txtNext = findViewById(R.id.txtNext)
        txtPrevious = findViewById(R.id.txtPrevious)
        txtQuote = findViewById(R.id.txtQuote)
        imgShare = findViewById(R.id.imgShare)

        setQuote(viewModel.currentQuote())

        txtNext.setOnClickListener {
            viewModel.nextQuote()
            setQuote(viewModel.currentQuote())
        }

        txtPrevious.setOnClickListener {
            viewModel.previousQuote()
            setQuote(viewModel.currentQuote())
        }

        imgShare.setOnClickListener {

            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_TEXT, viewModel.currentQuote().toString())

            startActivity(i)
        }


    }

    fun setQuote(quote: Quote) {
        txtQuote.text = quote.text
        txtAuthor.text = quote.author
    }


}
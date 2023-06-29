package com.rkbapps.quotiy

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context) : ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0


    init {
        quoteList = readQuote()
    }

    private fun readQuote(): Array<Quote> {
        val inputStream = context.assets.open("quote.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.defaultCharset())
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun currentQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]


}
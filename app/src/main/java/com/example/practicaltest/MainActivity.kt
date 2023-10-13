package com.example.practicaltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.practicaltest.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val columnCount = bindingMain.numOfColumns
        val rowCount = bindingMain.numOfRows
        val submit = bindingMain.submit

        submit.setOnClickListener {
            val columnCount_ = columnCount.text.toString()
            val rowCount_ = rowCount.text.toString()
            if ((TextUtils.isEmpty(columnCount_)) || (TextUtils.isEmpty(rowCount_))) {
                Toast.makeText(this, "Need both rows and columns values", Toast.LENGTH_SHORT)
                    .show()
            } else
                try {
                    val columns = columnCount_.toInt()
                    val rows = rowCount_.toInt()

                    val intent = Intent(this, ActivityGridLayout::class.java)

                    intent.putExtra("columnCount", columns)
                    intent.putExtra("rowCount", rows)
                    startActivity(intent)
                    Toast.makeText(this, "hello", Toast.LENGTH_LONG).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid values", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
package com.example.practicaltest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import com.example.practicaltest.databinding.ActivityGridLayoutBinding

class ActivityGridLayout : AppCompatActivity() {

    private lateinit var bindingGrid: ActivityGridLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGrid = ActivityGridLayoutBinding.inflate(layoutInflater)
        setContentView(bindingGrid.root)

        val grids = bindingGrid.gridLayout

        val columnCount = intent.getIntExtra("columnCount", 0)
        val rowCount = intent.getIntExtra("rowCount", 0)

        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                val boxes = ImageView(this)

                boxes.setBackgroundColor(Color.BLUE)
                val params = GridLayout.LayoutParams()
                params.width = 100
                params.height = 100
                params.rowSpec = GridLayout.spec(i)
                params.columnSpec = GridLayout.spec(j)
                params.setMargins(4, 4, 4, 4)
                boxes.layoutParams = params
                boxes.setOnClickListener { view ->
                    if (view.tag != "green") {
                        changeBoxColor(view)
                        notClickable(view)

                        val leftIndex = j - 1
                        val rightIndex = j + 1
                        val topIndex = i - 1
                        val bottomIndex = i + 1

                        if (leftIndex >= 0) {
                            val leftN = grids.getChildAt(i * columnCount + leftIndex)
                            changeBoxColor(leftN)
                            notClickable(leftN)
                        }

                        if (rightIndex < columnCount) {
                            val rightN = grids.getChildAt(i * columnCount + rightIndex)
                            changeBoxColor(rightN)
                            notClickable(rightN)
                        }

                        if (topIndex >= 0) {
                            val topN = grids.getChildAt(topIndex * columnCount + j)
                            changeBoxColor(topN)
                            notClickable(topN)
                        }

                        if (bottomIndex < rowCount) {
                            val bottomN = grids.getChildAt(bottomIndex * columnCount + j)
                            changeBoxColor(bottomN)
                            notClickable(bottomN)
                        }
                    }
                }

                grids.addView(boxes)
            }
        }
    }

    private fun changeBoxColor(view: View) {
        view.setBackgroundColor(Color.GREEN)
        view.tag = "green"
    }

    private fun notClickable(view: View) {
        view.isClickable = false
    }
}
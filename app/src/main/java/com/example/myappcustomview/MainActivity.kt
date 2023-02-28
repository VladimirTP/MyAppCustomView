package com.example.myappcustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.mode_button)

        button.setOnClickListener{
            onToggleModeClick()
        }
    }

    private fun onToggleModeClick() {
        val circleView = findViewById<CircleCounterView>(R.id.circle_view)
        circleView.toggleMode()
    }
}
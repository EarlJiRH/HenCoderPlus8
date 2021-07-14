package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.postDelayed
import kotlinx.android.synthetic.main.activity_material.*

class MaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        materialEditText.postDelayed(3000L) {
            materialEditText.userFloatingLabel = false
        }
    }
}
package com.areeb.fakejsoon.ui.base.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.areeb.fakejsoon.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
package com.example.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.jetpackdemo.database.AppDataBase
import com.example.jetpackdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //试图绑定
    //private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityMainBinding.inflate(layoutInflater)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


    }
}

package com.example.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.jetpackdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //试图绑定
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MainViewPagerFragment(), "MainBottomFragment")
        }
    }
}

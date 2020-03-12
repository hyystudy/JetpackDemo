package com.example.jetpackdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.databinding.FragmentGardenLayoutBinding

class GardenFragment : Fragment() {

    private lateinit var binding: FragmentGardenLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenLayoutBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {

    }
}
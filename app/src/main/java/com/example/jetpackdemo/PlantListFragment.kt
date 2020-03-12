package com.example.jetpackdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.databinding.FragmentPlantListLayoutBinding

class PlantListFragment : Fragment() {

    //跟布局试图
    private lateinit var binding: FragmentPlantListLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantListLayoutBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {

    }
}
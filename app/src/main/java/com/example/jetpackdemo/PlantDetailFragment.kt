package com.example.jetpackdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.jetpackdemo.databinding.FragmentPlantDetailLayoutBinding
import com.example.jetpackdemo.utilities.InjectorUtil
import com.example.jetpackdemo.viewmodels.PlantDetailViewModel

class PlantDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlantDetailLayoutBinding

    private val args: PlantDetailFragmentArgs by navArgs()

    private val plantDetailViewModel: PlantDetailViewModel by viewModels {
        InjectorUtil.getPlantDetailViewModelFactory(requireContext(), args.plantId)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantDetailLayoutBinding.inflate(inflater, container, false)
        context ?: return binding.root

        initView()
        return binding.root
    }

    private fun initView() {
        subscribeUi()
    }

    private fun subscribeUi() {

    }


    companion object {
        val TAG = PlantDetailFragment::class.java.simpleName
    }
}
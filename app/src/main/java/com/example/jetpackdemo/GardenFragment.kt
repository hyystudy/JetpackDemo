package com.example.jetpackdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.jetpackdemo.adapter.GardenPlantListAdapter
import com.example.jetpackdemo.adapter.PLANT_LIST_PAGE_INDEX
import com.example.jetpackdemo.database.entity.PlantAndGardenPlantings
import com.example.jetpackdemo.databinding.FragmentGardenLayoutBinding
import com.example.jetpackdemo.utilities.RepositoryProvider
import com.example.jetpackdemo.viewmodels.GardenPlantViewModel

class GardenFragment : Fragment() {

    private lateinit var binding: FragmentGardenLayoutBinding

    private val gardenPlantViewModel by viewModels<GardenPlantViewModel> {
        RepositoryProvider.getGardenPlantViewModelFactory(requireContext())
    }

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
        val gardenPlantListAdapter = GardenPlantListAdapter()
        binding.rvGardenList.adapter = gardenPlantListAdapter

        binding.addPlant.setOnClickListener {
            navigateToPlantList()
        }
        subscribeUi(gardenPlantListAdapter)

    }

    private fun navigateToPlantList() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            PLANT_LIST_PAGE_INDEX
    }

    private fun subscribeUi(adapter: GardenPlantListAdapter) {
        gardenPlantViewModel.getGardenPlants().observe(viewLifecycleOwner,
            Observer { list ->
                binding.hasPlants = !list.isNullOrEmpty()
                Log.d(TAG, "has plants ---> ${binding.hasPlants}")
                adapter.submitList(list)
            })
    }

    companion object {
        val TAG = GardenFragment::class.java.simpleName
    }
}
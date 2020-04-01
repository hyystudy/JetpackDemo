package com.example.jetpackdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.jetpackdemo.adapter.GardenPlantListAdapter
import com.example.jetpackdemo.adapter.PLANT_LIST_PAGE_INDEX
import com.example.jetpackdemo.databinding.FragmentGardenLayoutBinding
import com.example.jetpackdemo.utilities.RepositoryProvider
import com.example.jetpackdemo.viewmodels.GardenPlantViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class GardenFragment : Fragment() {

    private lateinit var mBinding: FragmentGardenLayoutBinding
    private val mDisposable = CompositeDisposable()

    private val mGardenPlantListAdapter by lazy {
        GardenPlantListAdapter()
    }

    private val gardenPlantViewModel by viewModels<GardenPlantViewModel> {
        RepositoryProvider.getGardenPlantViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentGardenLayoutBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initView()
        ensureSubscribe()
    }
    private fun initView() {

        mBinding.rvGardenList.adapter = mGardenPlantListAdapter

        mBinding.addPlant.setOnClickListener {
            navigateToPlantList()
        }

    }

    private fun navigateToPlantList() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            PLANT_LIST_PAGE_INDEX
    }

    private fun ensureSubscribe() {
//        gardenPlantViewModel.getGardenPlants().observe(viewLifecycleOwner,
//            Observer { list ->
//                binding.hasPlants = !list.isNullOrEmpty()
//                Log.d(TAG, "has plants ---> ${binding.hasPlants}")
//                adapter.submitList(list)
//            })

        val subscribeGardenPlants = gardenPlantViewModel.subScribeGardenPlants()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext{
                Log.d("GardenPlantViewModel", "data updated")
                mBinding.hasPlants = !it.isNullOrEmpty()
                mGardenPlantListAdapter.submitList(it)
            }
            .subscribe()
        mDisposable.addAll(subscribeGardenPlants)
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        mDisposable.clear()
        super.onDestroyView()
    }

    companion object {
        val TAG = GardenFragment::class.java.simpleName
    }
}
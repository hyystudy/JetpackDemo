package com.example.jetpackdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jetpackdemo.databinding.FragmentPlantDetailLayoutBinding
import com.example.jetpackdemo.utilities.InjectorUtil
import com.example.jetpackdemo.viewmodels.PlantDetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_plant_detail_layout.*

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plant_detail_layout, container, false)
        context ?: return binding.root

        initView()
        return binding.root
    }

    private fun initView() {
        subscribeUi()
    }

    private fun subscribeUi() {
        binding.apply {
            viewModel = plantDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        //设置返回按键
        binding.toolbar.setNavigationOnClickListener {view ->
            view.findNavController().navigateUp()
        }

        var isToolbarShown = false
        //设置何时显示toolbar title
        binding.plantDetailScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            // User scrolled past image to height of toolbar and the title text is
            // underneath the toolbar, so the toolbar should be shown.
            val shouldShowToolbar = scrollY > toolbar.height

            // The new state of the toolbar differs from the previous state; update
            // appbar and toolbar attributes.
            if (isToolbarShown != shouldShowToolbar) {

                isToolbarShown = shouldShowToolbar

                // Use shadow animator to add elevation if toolbar is shown
                binding.appbarLayout.isActivated = shouldShowToolbar

                // Show the plant name if toolbar is shown
                binding.toolbarLayout.isTitleEnabled = shouldShowToolbar

            }

        }

//        plantDetailViewModel.plant
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSuccess {
//                binding.plant = it
//            }.subscribe()
    }


    companion object {
        val TAG = PlantDetailFragment::class.java.simpleName
    }
}
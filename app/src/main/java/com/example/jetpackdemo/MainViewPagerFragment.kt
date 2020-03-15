package com.example.jetpackdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.adapter.GARDEN_PAGE_INDEX
import com.example.jetpackdemo.adapter.HomePagerAdapter
import com.example.jetpackdemo.adapter.PLANT_LIST_PAGE_INDEX
import com.example.jetpackdemo.databinding.FragmentMainViewpagerLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IndexOutOfBoundsException

class MainViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentMainViewpagerLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainViewpagerLayoutBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter =
            HomePagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabText(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

    }

    private fun getTabText(position: Int): String {
        return when(position) {
            GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabIcon(position: Int): Int = when(position){
        GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
        PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
        else -> throw IndexOutOfBoundsException()
    }
}
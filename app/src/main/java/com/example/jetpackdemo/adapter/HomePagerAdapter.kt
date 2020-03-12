package com.example.jetpackdemo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpackdemo.GardenFragment
import com.example.jetpackdemo.PlantListFragment

const val GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentCreators: Map<Int, () -> Fragment> = mapOf(
        GARDEN_PAGE_INDEX to { GardenFragment() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() }
    )

    override fun getItemCount(): Int = fragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return fragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}
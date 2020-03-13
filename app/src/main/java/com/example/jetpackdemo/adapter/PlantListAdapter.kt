package com.example.jetpackdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackdemo.MainViewPagerFragmentDirections
import com.example.jetpackdemo.database.entity.Plant
import com.example.jetpackdemo.databinding.PlantListItemBinding

class PlantListAdapter: ListAdapter<Plant, PlantListAdapter.PlantViewHolder>(PlantDiffCallback()) {

    companion object {
        private val TAG = PlantListAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(PlantListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
    }

    class PlantViewHolder(private val binding: PlantListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setOnClickListener {
                navigationTo(binding.plant!!, it)
            }
        }

        private fun navigationTo(plant: Plant, view: View) {
            val directions =
                MainViewPagerFragmentDirections.actionMainViewPagerFragmentToPlantDetailFragment(
                    plantId = plant.plantId
                )

            view.findNavController().navigate(directions)
        }

        fun bind(data: Plant) {
            binding.apply {
                //将data 复制给plant
                plant = data
                executePendingBindings()
            }
            //被 BindingAdapter代替 详见 (@link PlantDetailBindingAdapter bindImageFromUrl() )
//            Glide.with(binding.root).load(data.imageUrl).into(binding.ivPlantImage)
        }
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}
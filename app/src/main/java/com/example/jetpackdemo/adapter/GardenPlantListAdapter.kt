package com.example.jetpackdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.MainViewPagerFragmentDirections
import com.example.jetpackdemo.database.entity.PlantAndGardenPlantings
import com.example.jetpackdemo.databinding.GardenListItemBinding

class GardenPlantListAdapter: ListAdapter<PlantAndGardenPlantings, GardenPlantListAdapter.GardenPlantViewHolder>(GardenPlantDiffCallback()) {

    companion object {
        val TAG = GardenPlantListAdapter::class.java.simpleName
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenPlantViewHolder {
        val context = parent.context
        return GardenPlantViewHolder(GardenListItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: GardenPlantViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class GardenPlantViewHolder(private val binding: GardenListItemBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {  view ->
                binding.gardenPlant?.plant?.plantId?.let {
                    navigationToDetailPlant(view, it)
                }
            }
        }

        private fun navigationToDetailPlant(view: View, plantId: String) {
            val navigation =
                MainViewPagerFragmentDirections.actionMainViewPagerFragmentToPlantDetailFragment(
                    plantId
                )
            view.findNavController().navigate(navigation)
        }

        fun bind(plantAndGardenPlantings: PlantAndGardenPlantings?) {
           with(binding){
               gardenPlant = plantAndGardenPlantings
               executePendingBindings()
           }
        }
    }
}

class GardenPlantDiffCallback : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant == newItem.plant
    }

}
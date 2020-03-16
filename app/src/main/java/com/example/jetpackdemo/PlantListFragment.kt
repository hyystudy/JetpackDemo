package com.example.jetpackdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jetpackdemo.adapter.PlantListAdapter
import com.example.jetpackdemo.databinding.FragmentPlantListLayoutBinding
import com.example.jetpackdemo.utilities.RepositoryProvider
import com.example.jetpackdemo.viewmodels.PlantListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class PlantListFragment : Fragment() {

    //跟布局试图
    private lateinit var binding: FragmentPlantListLayoutBinding

    private val plantListViewModel: PlantListViewModel by viewModels {
        RepositoryProvider.getPlantListViewModel(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantListLayoutBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        initView()
        return binding.root
    }

    private fun initView() {

        val plantListAdapter = PlantListAdapter()
        binding.rvPlantList.adapter = plantListAdapter

        //用rxJava的时候 会出现 不知道何时数据插入完毕  只能监听workManager的异步任务执行状态
        //第一次初始化的时候可能出现 空白页面
//        val worker = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//            .addTag("SeedPlants").build()
//        //提交系统 执行任务
//        WorkManager.getInstance(requireContext().applicationContext).enqueue(worker)
//       // AppDataBase.getInstance(requireContext().applicationContext)
//        WorkManager.getInstance(requireContext()).getWorkInfosByTagLiveData("SeedPlants").observe(this.requireActivity(), Observer {
//            if (it.isNotEmpty()) {
//                val workInfo = it[0]
//                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
//                    subscribeUi(plantListAdapter)
//                }
//            }
//        })

        subscribeUi(plantListAdapter)

    }

    private fun subscribeUi(plantListAdapter: PlantListAdapter) {

        //rxjava2
//        val subscribe = plantListViewModel.getPlantsByDatabase()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d(TAG, "plant size is ---> ${it.size}")
//                plantListAdapter.submitList(it)
//            }
            //初始化数据库 plants
            plantListViewModel.getPlantsData()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.d(TAG, "database plants changed plants size is ${it.size}")
                plantListAdapter.submitList(it)
            }
            .subscribe()
            plantListViewModel.getPlantListByDataBase()

//        plantListViewModel.getPlantsByDatabase2().observe(viewLifecycleOwner, Observer { plants ->
//            Log.d(TAG, "plant size is ---> ${plants.size}")
//            plantListAdapter.submitList(plants)
//        })
    }


    companion object {
        val TAG = PlantListFragment::class.java.simpleName
    }
}
package com.example.jetpackdemo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpackdemo.database.entity.PlantAndGardenPlantings
import com.example.jetpackdemo.database.repository.GardenPlantRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class GardenPlantViewModel(private val gardenPlantRepository: GardenPlantRepository) : ViewModel() {

    //这里用BehaviorSubject得原因是因为 当用户到plant 详情页添加植物的时候  虽然flowable 数据会发送onNext事件
    //但由于这个时候 BehaviorSubject.hide().subscribe()的数据订阅者已经在fragment的onDestroyView 中被取消(mDisposable.clear())了
    //再次回到首页的时候在fragment的onViewCreated中调用 behaviorSubject.hide().subscribe() 重新订阅  由于BehaviorSubject的特性， 它会接收到订阅前的最后
    //一条数据以及订阅后的所有数据 所以这里可以使用BehaviorSubject
    //如果这里使用PublishSubject 由于在onDestroyView移除了数据订阅者  再次onViewCreated的时候重新订阅的时候
    //已经错过了flowable OnNext发射的数据 实际上是因为 PublishSubject只有在订阅后才能接收数据 订阅之前发送的数据PublishSubject 接收不到
    //从这里我们可以看出 BehaviorSubject 和 flowable的结合可以用于数据同步 也就是其他地方修改了数据后 通过这两者的监听 可以在其他页面同步刷新数据和页面
    private val mGardenPlants = BehaviorSubject.create<List<PlantAndGardenPlantings>>()
    private val mDisposable = CompositeDisposable()

    init {
        //当数据流 是Single 得时候 订阅后数据流只会发送一遍 如果本地得数据 有更改时 不会通知监听者
       // getGardenPlantsSingle()
        //当数据流 是 Observable 或者 Flowable 得时候 订阅后数据流一直保持连接状态 如果本地得数据 有更改时 会通知监听者
        getGardenPlantsFlowable()
    }

    private fun getGardenPlantsSingle() {
        val subscribe = gardenPlantRepository.getGardenPlantsSingle()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                Log.d("GardenPlantViewModel", "data doOnSuccess")
                mGardenPlants.onNext(it)
            }
            .subscribe()
        mDisposable.add(subscribe)
    }

    private fun getGardenPlantsFlowable() {
        val subscribe = gardenPlantRepository.getGardenPlantsFlowable()
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d("GardenPlantViewModel", "data doOnNext")
                mGardenPlants.onNext(it)
            }
            .subscribe()
        mDisposable.add(subscribe)
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    fun getGardenPlants() = gardenPlantRepository.getGardenPlants()

    fun subScribeGardenPlants(): Observable<List<PlantAndGardenPlantings>> = mGardenPlants.hide()
}
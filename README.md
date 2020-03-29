#### 概览

​		这是一个Jetpack入门Demo, 里面用到的基本都是Jetpack的一些组件 如 DataBinding   ViewModel  LiveData  。。。等，整体是根据Jetpack

[官方文档]: https://developer.android.com/jetpack

以及 官方给的一个

[Demo]: https://github.com/android/sunflower

作为参考以及学习入门，一步一步将多个Jepack组件结合起来。

#### 整体架构图

![](C:\Users\86150\Desktop\jetpack架构图.png)

简单描述下各个模块的职责：

##### ViewModel:

连接View层 和Model(Data层)的桥梁， 直接持有Data Repository的引用以方便为View层提供数据

##### Repository：

数据仓库，简单俩说就是为ViewModel提供数据的，它的数据一般来源于 **Local**、**NetWork**、**DataBase**，

通常我们的做法是一个模块一个Repository，相关性比较大的数据提供一般都在一个Repository中

##### View：

视图层，一般会持有ViewModel的引用，方便调用ViewModel暴露的方法来获取数据并监听ViewModel中数据层的变化以更新视图

**各个Jetpack 组件的使用入门 待更新。。。**
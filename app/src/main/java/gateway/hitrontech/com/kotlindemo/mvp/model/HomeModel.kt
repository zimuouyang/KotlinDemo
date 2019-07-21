package gateway.hitrontech.com.kotlindemo.mvp.model

import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean
import gateway.hitrontech.com.kotlindemo.net.RetrofitManager
import gateway.hitrontech.com.kotlindemo.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class HomeModel {
    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int): Observable<HomeBean> {
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{

        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}
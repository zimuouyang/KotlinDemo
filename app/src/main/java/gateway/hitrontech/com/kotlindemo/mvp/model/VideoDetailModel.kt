package gateway.hitrontech.com.kotlindemo.mvp.model

import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean
import gateway.hitrontech.com.kotlindemo.net.RetrofitManager
import gateway.hitrontech.com.kotlindemo.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class VideoDetailModel {
    fun requestRelatedData(id:Long): Observable<HomeBean.Issue> {

        return RetrofitManager.service.getRelatedData(id)
                .compose(SchedulerUtils.ioToMain())
    }
}
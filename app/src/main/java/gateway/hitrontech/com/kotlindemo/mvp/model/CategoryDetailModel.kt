package gateway.hitrontech.com.kotlindemo.mvp.model

import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean
import gateway.hitrontech.com.kotlindemo.net.RetrofitManager
import gateway.hitrontech.com.kotlindemo.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class CategoryDetailModel {

    fun getCategoryDetailList(id: Long) : Observable<HomeBean.Issue> {
        return RetrofitManager.service.getCategoryDetailList(id).compose(SchedulerUtils.ioToMain())
    }

    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}
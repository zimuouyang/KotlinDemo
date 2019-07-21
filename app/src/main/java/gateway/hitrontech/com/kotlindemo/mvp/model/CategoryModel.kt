package gateway.hitrontech.com.kotlindemo.mvp.model

import gateway.hitrontech.com.kotlindemo.mvp.model.bean.CategoryBean
import gateway.hitrontech.com.kotlindemo.net.RetrofitManager
import gateway.hitrontech.com.kotlindemo.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class CategoryModel {
    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
                .compose(SchedulerUtils.ioToMain())
    }
}
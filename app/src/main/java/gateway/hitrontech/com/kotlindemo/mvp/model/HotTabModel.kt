package gateway.hitrontech.com.kotlindemo.mvp.model

import gateway.hitrontech.com.kotlindemo.mvp.model.bean.TabInfoBean
import gateway.hitrontech.com.kotlindemo.net.RetrofitManager
import gateway.hitrontech.com.kotlindemo.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class HotTabModel {
    /**
     * 获取 TabInfo
     */
    fun getTabInfo(): Observable<TabInfoBean> {
        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }

}
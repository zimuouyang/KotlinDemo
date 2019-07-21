package gateway.hitrontech.com.kotlindemo.mvp.concat

import gateway.hitrontech.com.kotlindemo.base.BaseView
import gateway.hitrontech.com.kotlindemo.base.IPresenter
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

interface RankContract {
    interface View : BaseView {
        /**
         * 设置排行榜的数据
         */
        fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun requestRankList(apiUrl:String)
    }
}
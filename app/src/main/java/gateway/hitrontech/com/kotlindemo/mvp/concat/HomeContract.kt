package gateway.hitrontech.com.kotlindemo.mvp.concat

import gateway.hitrontech.com.kotlindemo.base.BaseView
import gateway.hitrontech.com.kotlindemo.base.IPresenter
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

interface HomeContract {
    interface View : BaseView {
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList:ArrayList<HomeBean.Issue.Item>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)
    }

    interface Presenter : IPresenter<View>{

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()
    }

}
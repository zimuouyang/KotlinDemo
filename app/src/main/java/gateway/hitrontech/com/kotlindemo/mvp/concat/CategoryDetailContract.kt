package gateway.hitrontech.com.kotlindemo.mvp.concat

import gateway.hitrontech.com.kotlindemo.base.BaseView
import gateway.hitrontech.com.kotlindemo.base.IPresenter
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

interface CategoryDetailContract {
    interface View : BaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)
    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}
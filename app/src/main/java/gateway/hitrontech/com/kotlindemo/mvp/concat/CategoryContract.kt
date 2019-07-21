package gateway.hitrontech.com.kotlindemo.mvp.concat

import gateway.hitrontech.com.kotlindemo.base.BaseView
import gateway.hitrontech.com.kotlindemo.base.IPresenter
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.CategoryBean

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

interface CategoryContract {
    interface View : BaseView {
        fun showCategory(actegoryList: ArrayList<CategoryBean>)

        fun showError(errMsg : String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {
        fun getCategoryData()
    }
}
package gateway.hitrontech.com.kotlindemo.mvp.concat

import gateway.hitrontech.com.kotlindemo.base.BaseView
import gateway.hitrontech.com.kotlindemo.base.IPresenter
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.TabInfoBean

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

interface HotTabContract {
    interface View:BaseView{
        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode:Int)
    }


    interface Presenter: IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }
}
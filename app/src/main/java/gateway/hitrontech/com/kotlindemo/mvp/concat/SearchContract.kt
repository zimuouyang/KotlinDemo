package gateway.hitrontech.com.kotlindemo.mvp.concat

import gateway.hitrontech.com.kotlindemo.base.BaseView
import gateway.hitrontech.com.kotlindemo.base.IPresenter
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

interface SearchContract {
        interface View : BaseView {
            /**
             * 设置热门关键词数据
             */
            fun setHotWordData(string: ArrayList<String>)

            /**
             * 设置搜索关键词返回的结果
             */
            fun setSearchResult(issue: HomeBean.Issue)
            /**
             * 关闭软件盘
             */
            fun closeSoftKeyboard()

            /**
             * 设置空 View
             */
            fun setEmptyView()


            fun showError(errorMsg: String,errorCode:Int)
        }


        interface Presenter : IPresenter<View> {
            /**
             * 获取热门关键字的数据
             */
            fun requestHotWordData()

            /**
             * 查询搜索
             */
            fun querySearchData(words:String)

            /**
             * 加载更多
             */
            fun loadMoreData()
        }
    }
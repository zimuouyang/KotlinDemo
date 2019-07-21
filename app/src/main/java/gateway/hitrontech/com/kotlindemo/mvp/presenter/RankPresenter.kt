package gateway.hitrontech.com.kotlindemo.mvp.presenter

import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.RankContract
import gateway.hitrontech.com.kotlindemo.mvp.model.RankModel
import gateway.hitrontech.com.kotlindemo.net.ExceptionHandle

class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {
    private val rankModel by lazy { RankModel() }
    override fun requestRankList(apiUrl: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = rankModel.requestRankList(apiUrl)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRankList(issue.itemList)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }
}
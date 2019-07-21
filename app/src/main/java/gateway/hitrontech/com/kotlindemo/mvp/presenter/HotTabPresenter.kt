package gateway.hitrontech.com.kotlindemo.mvp.presenter

import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.HotTabContract
import gateway.hitrontech.com.kotlindemo.mvp.model.HotTabModel
import gateway.hitrontech.com.kotlindemo.net.ExceptionHandle

class HotTabPresenter : BasePresenter<HotTabContract.View>(), HotTabContract.Presenter {

    private val hotTabModel by lazy {
        HotTabModel()
    }

    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val dispose = hotTabModel.getTabInfo().subscribe({ tabInfo ->
            mRootView?.apply { setTabInfo(tabInfo) }
        }, { throwable ->
            mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
        })
        addSubscription(dispose)
    }
}
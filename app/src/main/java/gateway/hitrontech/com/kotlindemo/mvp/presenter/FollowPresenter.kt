package gateway.hitrontech.com.kotlindemo.mvp.presenter

import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.FollowContract
import gateway.hitrontech.com.kotlindemo.mvp.model.FollowModel
import gateway.hitrontech.com.kotlindemo.net.ExceptionHandle

class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {
    private val followModel by lazy { FollowModel() }

    private var nextPageUrl: String? = null

    override fun loadMoreData() {
        val dispose = nextPageUrl?.let {
            followModel.loadMoreData(it).subscribe(
                    { issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setFollowInfo(issue)
                        }
                    },
                    { t ->
                        mRootView?.apply { showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode) }
                    }
            )
        }
        if (dispose != null) {
            addSubscription(dispose)
        }
    }

    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = followModel.requestFollowList()
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

}
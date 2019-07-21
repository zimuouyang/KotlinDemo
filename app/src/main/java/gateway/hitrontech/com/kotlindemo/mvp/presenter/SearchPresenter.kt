package gateway.hitrontech.com.kotlindemo.mvp.presenter

import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.SearchContract
import gateway.hitrontech.com.kotlindemo.mvp.model.SearchModel
import gateway.hitrontech.com.kotlindemo.net.ExceptionHandle

class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {
    private var nextPageUrl: String? = null
    private val searchModel by lazy {
        SearchModel()
    }

    override fun requestHotWordData() {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        val dispose = searchModel.requestHotWordData().subscribe({ strings ->
            mRootView?.apply {
                dismissLoading()
                setHotWordData(strings)
            }
        }, { throwable ->
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
            }
        })
        addSubscription(dispose)
    }

    override fun querySearchData(words: String) {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.getSearchResult(words)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        if (issue.count > 0 && issue.itemList.size > 0) {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        } else
                            setEmptyView()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        )
    }

    override fun loadMoreData() {
        checkViewAttached()
        nextPageUrl?.let {
            addSubscription(disposable = searchModel.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    }))
        }
    }
}
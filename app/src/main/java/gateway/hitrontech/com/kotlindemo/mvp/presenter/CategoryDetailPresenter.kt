package gateway.hitrontech.com.kotlindemo.mvp.presenter

import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.CategoryDetailContract
import gateway.hitrontech.com.kotlindemo.mvp.model.CategoryDetailModel

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class CategoryDetailPresenter : BasePresenter<CategoryDetailContract.View>(), CategoryDetailContract.Presenter {
    private val categoryDeatil by lazy {
        CategoryDetailModel()
    }

    private var nextPageUrl : String? = null

    override fun getCategoryDetailList(id: Long) {
       checkViewAttached()
        val dispose = categoryDeatil.getCategoryDetailList(id).subscribe({issue -> mRootView ?.apply { nextPageUrl = issue.nextPageUrl
            setCateDetailList(issue.itemList)
        }}, {
            throwable -> mRootView?.apply { showError(throwable.toString()) }
        })
        addSubscription(dispose)
    }

    override fun loadMoreData() {
        val disposable = nextPageUrl?.let { categoryDeatil.loadMoreData(it).subscribe({
            issue -> mRootView?.apply { nextPageUrl = issue.nextPageUrl
            setCateDetailList(issue.itemList)
            }
        }, {
            throwable -> mRootView?.apply { showError(throwable.toString()) }
        })}

        disposable?.let { addSubscription(it) }
    }

}
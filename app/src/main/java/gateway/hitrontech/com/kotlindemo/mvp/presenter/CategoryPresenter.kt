package gateway.hitrontech.com.kotlindemo.mvp.presenter

import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.CategoryContract
import gateway.hitrontech.com.kotlindemo.mvp.model.CategoryModel
import gateway.hitrontech.com.kotlindemo.net.ExceptionHandle

class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel : CategoryModel by lazy {
        CategoryModel()
    }
    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.getCategoryData().subscribe({categoryList -> mRootView?.apply {
            dismissLoading()
            showCategory(categoryList)
        }}, {
            t -> mRootView ?.apply { showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode) }
        })
        addSubscription(disposable)
    }
}
package gateway.hitrontech.com.kotlindemo.base

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

interface IPresenter<in V : BaseView> {
    fun attachView(rootView : V)
    fun detachView()
}
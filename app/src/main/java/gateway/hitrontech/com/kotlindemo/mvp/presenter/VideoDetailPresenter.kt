package gateway.hitrontech.com.kotlindemo.mvp.presenter

import android.app.Activity
import gateway.hitrontech.com.kotlindemo.utils.DisplayManager
import gateway.hitrontech.com.kotlindemo.MyApplication
import gateway.hitrontech.com.kotlindemo.base.BasePresenter
import gateway.hitrontech.com.kotlindemo.mvp.concat.VideoDetailContract
import gateway.hitrontech.com.kotlindemo.mvp.model.VideoDetailModel
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.HomeBean
import gateway.hitrontech.com.kotlindemo.net.ExceptionHandle
import gateway.hitrontech.com.kotlindemo.utils.NetworkUtil
import gateway.hitrontech.com.kotlindemo.utils.dataFormat
import gateway.hitrontech.com.kotlindemo.utils.showToast

class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>(), VideoDetailContract.Presenter {
    private val videoDetailModel: VideoDetailModel by lazy {

        VideoDetailModel()
    }

    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {
        val playInfo = itemInfo.data?.playInfo
        val netType = NetworkUtil.isWifi(MyApplication.context)

        checkViewAttached()
        if (playInfo!!.size > 1) {
            // 当前网络是 Wifi环境下选择高清的视频
            if (netType) {
                for (i in playInfo) {
                    if (i.type == "high") {
                        val playUrl = i.url
                        mRootView?.setVideo(playUrl)
                        break
                    }
                }
            } else {
                //否则就选标清的视频
                for (i in playInfo) {
                    if (i.type == "normal") {
                        val playUrl = i.url
                        mRootView?.setVideo(playUrl)
                        (mRootView as Activity).showToast("本次消耗${(mRootView as Activity)
                                .dataFormat(i.urlList[0].size)}流量")
                        break
                    }
                }
            }
        } else {
            mRootView?.setVideo(itemInfo.data.playUrl)
        }

        //设置背景
        val backgroundUrl = itemInfo.data.cover.blurred + "/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
        backgroundUrl.let { mRootView?.setBackground(it) }

        mRootView?.setVideoInfo(itemInfo)
    }

    /**
     * 请求相关的视频数据
     */
    override fun requestRelatedVideo(id: Long) {
        mRootView?.showLoading()
        val disposable = videoDetailModel.requestRelatedData(id)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRecentRelatedVideo(issue.itemList)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        setErrorMsg(ExceptionHandle.handleException(t))
                    }
                })

        addSubscription(disposable)

    }
}
package gateway.hitrontech.com.kotlindemo.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import gateway.hitrontech.com.kotlindemo.MyApplication
import gateway.hitrontech.com.kotlindemo.view.MultipleStatusView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by ShuaiLei Zhao on 3/7/2019.
 */
 
abstract class BaseFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    //视图是否加载完毕
    private var mIsViewPrepare = false
    //已经下载过数据
    private var mHasLoadData = false

    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIsViewPrepare = true
        initView()
        lazyLoadDataIfPrepared()
        mLayoutStatusView ?.setOnRetryClickListener(mRetryLoadListener)

    }

    open val mRetryLoadListener : View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && mIsViewPrepare && !mHasLoadData) {
            lazyLoad()
            mHasLoadData = true
        }
    }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { MyApplication.getRefWatcher(it)?.watch(activity) }
    }


    /**
     * 重写申请权限，交由EasyPermissions进行处理
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d("EasyPermissions", "获取的用户权限$perms")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(activity, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }
}
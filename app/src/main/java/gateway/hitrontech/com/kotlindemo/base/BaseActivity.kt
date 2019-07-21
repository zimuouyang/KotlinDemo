package gateway.hitrontech.com.kotlindemo.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import gateway.hitrontech.com.kotlindemo.MyApplication
import gateway.hitrontech.com.kotlindemo.view.MultipleStatusView
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by ShuaiLei Zhao on 3/7/2019.
 */

abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initView()
        initData()
        start()
        initListener()
    }

    private fun initListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }

    /**
     * 加载布局
     */
    abstract fun layout() : Int

    /**
     * 加载View
     */
    abstract fun initView()

    abstract fun initData()

    /**
     * 开始请求
     */
    abstract fun start()

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(this)?.watch(this)
    }

    fun openKeyBord(editext: EditText, context: Context) {
        val immm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        immm.showSoftInput(editext, InputMethodManager.RESULT_SHOWN)
        immm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun closeKeyBord(editext: EditText, context: Context) {
        val immm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        immm.hideSoftInputFromWindow(editext.windowToken, InputMethodManager.RESULT_HIDDEN)
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
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }
}
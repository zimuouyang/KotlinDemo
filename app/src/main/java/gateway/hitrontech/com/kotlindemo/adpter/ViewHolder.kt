package gateway.hitrontech.com.kotlindemo.adpter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    //
    private var mView : SparseArray<View>? = null

    init {
        mView = SparseArray()
    }

    fun <T : View> getView(viewId : Int) : T {
        var view : View? = mView?.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            mView?.put(viewId, view)
        }

        return view as T
    }

    fun <T : ViewGroup> getViewGroup(viewId: Int): T {
        //对已有的view做缓存
        var view: View? = mView?.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            mView?.put(viewId, view)
        }
        return view as T
    }

    fun setText(viewId: Int, text: CharSequence) : ViewHolder {
        val view = getView<TextView>(viewId)
        view.text = "" + text
        return this
    }

    fun setHintText(viewId: Int, text: CharSequence): ViewHolder {
        val view = getView<TextView>(viewId)
        view.hint = "" + text
        return this
    }

    //加载本地图片
    fun setImageResource(viewId: Int, resId: Int): ViewHolder {
        val iv = getView<ImageView>(viewId)
        iv.setImageResource(resId)
        return this
    }

    fun setImagePath(viewId: Int, imageLoader: HolderImageLoader) : ViewHolder {
        val imageView = getView<ImageView>(viewId)
        imageLoader.lodaImage(imageView, imageLoader.path)
        return this
    }

    abstract class HolderImageLoader(val path : String) {
        abstract fun lodaImage(iv : ImageView, path: String)
    }

    fun setVisiable(viewId: Int, visibility: Int) : ViewHolder{
        getView<View>(viewId).visibility = visibility
        return this
    }

    fun setOnItemClickListener(listener: () -> Unit) {
        itemView.setOnClickListener{listener}
    }

    fun setOnItemLongClickListener(listener: () -> Boolean) {
        itemView.setOnLongClickListener {listener.invoke() }
    }
}
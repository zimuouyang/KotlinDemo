package gateway.hitrontech.com.kotlindemo.adpter

import android.content.Context
import gateway.hitrontech.com.kotlindemo.mvp.model.bean.CategoryBean

class CategoryAdapter(mContext : Context, categoryList : ArrayList<CategoryBean>, layoutId : Int) : CommonAdapter<CategoryBean>(mContext, categoryList, layoutId){
    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {
        
    }
}
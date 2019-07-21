package gateway.hitrontech.com.kotlindemo.mvp.model.bean

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

data class TabInfoBean(val tabInfo: TabInfo) {
    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)
}
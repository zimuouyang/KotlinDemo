package gateway.hitrontech.com.kotlindemo.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

class TabEntity (var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}

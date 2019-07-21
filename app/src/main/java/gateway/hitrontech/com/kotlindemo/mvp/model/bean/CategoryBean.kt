package gateway.hitrontech.com.kotlindemo.mvp.model.bean

import java.io.Serializable

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

class CategoryBean(val id: Long, val name: String, val description: String,
                   val bgPicture: String, val bgColor: String, val headerImage: String) : Serializable{
}
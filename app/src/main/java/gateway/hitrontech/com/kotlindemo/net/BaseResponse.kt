package gateway.hitrontech.com.kotlindemo.net

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

class BaseResponse<T>(val code : Int, val msg : String, val data : T) {
}
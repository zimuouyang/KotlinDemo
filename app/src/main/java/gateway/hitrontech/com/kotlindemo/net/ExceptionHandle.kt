package gateway.hitrontech.com.kotlindemo.net

import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

class ExceptionHandle {
    companion object {
        var errorCode = ErrorStatus.UNKNOWN_ERROR
        var errorMsg = "请求失败，请稍后重试"

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is SocketTimeoutException) {
                Logger.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is ConnectException) {
                Logger.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException) {
                Logger.e("TAG", "数据解析异常: " + e.message)
                errorMsg = "数据解析异常"
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is ApiException) {
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {
                Logger.e("TAG", "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                errorCode = ErrorStatus.SERVER_ERROR
            } else {
                try {
                    Logger.e("TAG", "错误: " + e.message)
                } catch (e1: Exception) {
                    Logger.e("TAG", "未知错误Debug调试 ")
                }

                errorMsg = "未知错误，可能抛锚了吧~"
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
            return errorMsg
        }

    }
}
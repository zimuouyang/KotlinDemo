package gateway.hitrontech.com.kotlindemo.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.security.MessageDigest

/**
 * Created by ShuaiLei Zhao on 3/7/2019.
 */
 
class AppUtils private constructor() {
    init {
        throw Error("Dont not need instantiate")
    }

    companion object {
        private val DEBUG = true
        private val TAG = "AppUtils"

        /**
         * 获取版本号
         */
        fun getVetCode(context: Context) : Int {
            var vercode = -1
            try {
                val packageName = context.packageName
                vercode = context.packageManager.getPackageInfo(packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return vercode
        }

        /**
         * 将签名文件转为需要的32位签名
         */
        private fun hexDigest(paramArrayOfByte: ByteArray): String {
            val hexDigits = charArrayOf(48.toChar(), 49.toChar(), 50.toChar(), 51.toChar(), 52.toChar(), 53.toChar(), 54.toChar(), 55.toChar(), 56.toChar(), 57.toChar(), 97.toChar(), 98.toChar(), 99.toChar(), 100.toChar(), 101.toChar(), 102.toChar())
            try {
                val localMessageDigest = MessageDigest.getInstance("MD5")
                localMessageDigest.update(paramArrayOfByte)
                val arrayOfByte = localMessageDigest.digest()
                val arrayOfChar = CharArray(32)
                var i = 0
                var j = 0
                while (true) {
                    if (i >= 16) {
                        return String(arrayOfChar)
                    }
                    val k = arrayOfByte[i].toInt()
                    arrayOfChar[j] = hexDigits[0xF and k.ushr(4)]
                    arrayOfChar[++j] = hexDigits[k and 0xF]
                    i++
                    j++
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

        fun getMobileModel(): String {
            var model: String? = Build.MODEL
            model = model?.trim { it <= ' ' } ?: ""
            return model
        }
    }
}
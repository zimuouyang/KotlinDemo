package gateway.hitrontech.com.kotlindemo.net

import java.lang.RuntimeException

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */

class ApiException : RuntimeException{
    private var code : Int? = null

    constructor(throwable: Throwable, code : Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}
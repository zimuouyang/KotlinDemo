package gateway.hitrontech.com.kotlindemo.rx.scheduler

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

object SchedulerUtils {

    fun <T>ioToMain(): IOMainScheduler<T> {
        return IOMainScheduler()
    }
}
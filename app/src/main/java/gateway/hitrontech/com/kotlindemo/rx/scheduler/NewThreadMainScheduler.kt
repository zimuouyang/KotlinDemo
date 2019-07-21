package gateway.hitrontech.com.kotlindemo.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

class NewThreadMainScheduler<T>: BaseSchedule<T>(Schedulers.newThread(), AndroidSchedulers.mainThread()) {
}
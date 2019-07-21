package gateway.hitrontech.com.kotlindemo.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */
 
class IOMainScheduler<T> : BaseSchedule<T>(Schedulers.io(), AndroidSchedulers.mainThread())
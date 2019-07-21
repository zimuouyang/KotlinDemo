package gateway.hitrontech.com.kotlindemo.rx.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

/**
 * Created by ShuaiLei Zhao on 5/7/2019.
 */

abstract class BaseSchedule<T> protected constructor(private val subscribeOnScheduler : Scheduler , private val observeOnScheduler : Scheduler) :
        ObservableTransformer<T, T> , SingleTransformer<T, T>, MaybeTransformer<T, T>, FlowableTransformer<T, T>, CompletableTransformer{

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler)
    }
}
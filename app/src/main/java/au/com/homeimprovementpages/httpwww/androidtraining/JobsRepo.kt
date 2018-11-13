package au.com.homeimprovementpages.httpwww.androidtraining

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * This class
 *
 * Author: michaeldelaney
 * Created: 17/10/18
 */
class JobsRepo {

    fun getJobsObservable() =
            Observable.create(object : ObservableOnSubscribe<List<Job>> {
                override fun subscribe(e: ObservableEmitter<List<Job>>) {
                    e.onNext(mutableListOf(
                            Job(1, "Bob", "Bobs mowing",
                                    "Heres your quote"),
                            Job(1, "Betty", "Hair Dresser",
                                    "Heres your quote")

                    ))
                }

            })
}
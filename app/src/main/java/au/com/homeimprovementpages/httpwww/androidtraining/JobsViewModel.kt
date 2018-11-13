package au.com.homeimprovementpages.httpwww.androidtraining

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * This class
 *
 * Author: michaeldelaney
 * Created: 17/10/18
 */
class JobsViewModel(private val jobsModel: JobsRepo): ViewModel() {

    val liveData = MutableLiveData<List<Job>>()
    private val jobs = mutableListOf<Job>()

    fun jobDeleted(index: Int) {
        if(index >= 0 && index < jobs.size) {
            jobs.removeAt(index)
            liveData.value = jobs
        }
    }

    fun addJobs() {
        jobsModel.getJobsObservable().subscribe({
            jobs.addAll(it)
            liveData.value = jobs
        })
    }
}
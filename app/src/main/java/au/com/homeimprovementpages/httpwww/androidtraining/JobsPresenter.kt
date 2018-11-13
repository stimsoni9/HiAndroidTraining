package au.com.homeimprovementpages.httpwww.androidtraining

import android.arch.lifecycle.Observer

/**
 * This class
 *
 * Author: michaeldelaney
 * Created: 17/10/18
 */
class JobsPresenter(val view: Contract, val viewModel: JobsViewModel) {

    fun init() {
        viewModel.liveData.observe(view, object: Observer<List<Job>> {
            override fun onChanged(jobs: List<Job>?) {
                jobs?.let(view::setItems)
            }
        })
    }

    fun jobDeleted(index: String) {
        if(index.isNotBlank() && index.toIntOrNull() != null) {
            viewModel.jobDeleted(index.toInt())
        }
    }

    fun addJobs() {
        viewModel.addJobs()
    }

    interface Contract : Presenter {
        fun addItem(job: Job)
        fun setItems(job: List<Job>)
        fun removeItemAt(index: Int)
    }
}
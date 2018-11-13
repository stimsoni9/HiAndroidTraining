package au.com.homeimprovementpages.httpwww.androidtraining

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * This class
 *
 * Author: michaeldelaney
 * Created: 17/10/18
 */
class JobsFragment : Fragment(), JobsPresenter.Contract {

    private var myJobsPresenter: JobsPresenter? = null
    private var myLayoutManager: LinearLayoutManager? = null
    private var jobsAdapter: JobsAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return JobsViewModel(JobsRepo()) as T
            }
        }
        myJobsPresenter = JobsPresenter(this,
                ViewModelProviders
                .of(this, viewModelFactory).get(JobsViewModel::class.java))
        myJobsPresenter?.init()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myLayoutManager = GridLayoutManager(context, 1)
        jobs_recList.layoutManager = myLayoutManager
        jobsAdapter = JobsAdapter()
        jobs_recList.adapter = jobsAdapter
        jobs_recList.itemAnimator = DefaultItemAnimator()

        jobs_btnAdd.setOnClickListener { myJobsPresenter?.addJobs() }
        jobs_btnRemove.setOnClickListener {
            myJobsPresenter?.jobDeleted(jobs_txbLineNum.text.toString())
        }
    }

    override fun addItem(job: Job) {
        jobsAdapter?.addJob(job)
    }

    override fun setItems(jobs: List<Job>) {
        jobsAdapter?.setJobs(jobs)
    }

    override fun removeItemAt(index: Int) {
        jobsAdapter?.removeJobAt(index)
    }
}
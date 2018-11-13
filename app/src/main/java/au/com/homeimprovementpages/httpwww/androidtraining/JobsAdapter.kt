package au.com.homeimprovementpages.httpwww.androidtraining

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * This class
 *
 * Author: michaeldelaney
 * Created: 17/10/18
 */
class JobsAdapter : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {
    val jobs = mutableListOf<Job>()

    fun addJob(job: Job) {
        var index = jobs.size
        jobs.add(job)
        notifyItemInserted(index)
    }

    fun setJobs(newJobList: List<Job>) {
        jobs.clear()
        jobs.addAll(newJobList)
        notifyDataSetChanged()
    }

    fun removeJobAt(index: Int) {
        jobs.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(inflater.inflate(R.layout.job_item, parent, false))
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    override fun onBindViewHolder(vh: ViewHolder, index: Int) {
        vh.update(jobs[index])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tradieName: TextView
        val messageText: TextView
        val businessName: TextView

        init {
            tradieName = itemView.findViewById<TextView>(R.id.textView1)
            businessName = itemView.findViewById<TextView>(R.id.textView2)
            messageText = itemView.findViewById<TextView>(R.id.textView3)
        }
        fun update(job: Job) {
            tradieName.text = job.tradieName
            businessName.text = job.companyName
            messageText.text = job.lastMessageText
        }
    }
}


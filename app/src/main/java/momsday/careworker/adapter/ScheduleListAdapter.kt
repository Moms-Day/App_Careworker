package momsday.careworker.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import momsday.careworker.R
import momsday.careworker.model.ScheduleModel

class ScheduleListAdapter(val models: ArrayList<ScheduleModel>) : RecyclerView.Adapter<ScheduleListAdapter.ScheduleListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleListViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule_list, parent, false).let {
        ScheduleListViewHolder(it)
    }


    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: ScheduleListViewHolder, position: Int) = holder.bind(models[position])

    inner class ScheduleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time = itemView.findViewById<TextView>(R.id.item_schedule_time)
        val info = itemView.findViewById<TextView>(R.id.item_schedule_info)
        fun bind(model: ScheduleModel) {
            time.text = "${model.startTime} ~ ${model.endTime}"
            info.text = model.info
        }

    }
}

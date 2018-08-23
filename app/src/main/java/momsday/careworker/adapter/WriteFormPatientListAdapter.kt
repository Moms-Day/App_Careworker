package momsday.careworker.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import momsday.careworker.R
import momsday.careworker.model.WriteFormListModel

class WriteFormPatientListAdapter(val models: ArrayList<WriteFormListModel>) : RecyclerView.Adapter<WriteFormPatientListAdapter.WriteFormPatientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteFormPatientViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_write_form_patient_item, parent, false).let {
            WriteFormPatientViewHolder(it)
        }
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: WriteFormPatientViewHolder, position: Int) {
        holder.bind(models[position])
    }

    inner class WriteFormPatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_writeFormPatient_name)
        fun bind(model: WriteFormListModel) {
            name.text = model.name
        }

    }
}

package momsday.careworker.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import momsday.careworker.R
import momsday.careworker.model.PatientListModel
import momsday.careworker.model.WriteFormListModel
import momsday.careworker.ui.writeForm.SelectFormFragment
import momsday.careworker.ui.writeForm.WriteFormActivity
import java.util.ArrayList

class WriteFormPatientListAdapter(val models: ArrayList<PatientListModel>) : RecyclerView.Adapter<WriteFormPatientListAdapter.WriteFormPatientViewHolder>() {
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
        fun bind(model: PatientListModel) {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, WriteFormActivity::class.java)
                intent.putExtra("name", model.name)
                intent.putExtra("id", model.id)
                intent.putExtra("isAll", false)
                itemView.context.startActivity(intent)
            }
            name.text = model.name
        }

    }
}

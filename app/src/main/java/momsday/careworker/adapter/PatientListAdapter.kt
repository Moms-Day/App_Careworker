package momsday.careworker.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

import momsday.careworker.model.PatientListModel
import momsday.careworker.R
import momsday.careworker.ui.PatientInfo.PatientInfoActivity

class PatientListAdapter(private val models: ArrayList<PatientListModel>) : RecyclerView.Adapter<PatientListViewBinder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientListViewBinder {
        val layoutId: Int = when (viewType) {
            PatientListModel.VIEWTYPE_HEADER -> R.layout.item_patient_list_header
            PatientListModel.VIEWTYPE_PATIENT -> R.layout.item_patient_list
            PatientListModel.VIEWTYPE_REQUEST -> R.layout.item_patient_list_request
            else -> 0
        }
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return PatientListItemFactory.getViewHolder(viewType, itemView)!!
    }

    override fun onBindViewHolder(holder: PatientListViewBinder, position: Int) = holder.bind(models[position])

    override fun getItemCount() = models.size

    override fun getItemViewType(position: Int) = models[position].viewType
}

class PatientListViewHolder(itemView: View) : PatientListViewBinder(itemView) {

    var name: TextView = itemView.findViewById(R.id.tv_patientListItem_name)
    var info: TextView = itemView.findViewById(R.id.tv_patientListItem_age)
    var protectorName: TextView = itemView.findViewById(R.id.tv_patientListItem_protector)

    override fun bind(model: PatientListModel) {
        name.text = model.name
        protectorName.text = model.protectorName
        info.text = model.info
        itemView.setOnClickListener { v ->
            val intent = Intent(itemView.context, PatientInfoActivity::class.java)
            itemView.context.startActivity(intent)
        }
    }
}

class PatientListHeaderViewHolder(itemView: View) : PatientListViewBinder(itemView) {

    val listInfo: TextView = itemView.findViewById(R.id.tv_patientListItemHeader_info)

    override fun bind(model: PatientListModel) {
        listInfo.text = model.name
    }
}

class PatientListRequestViewHolder(itemView: View) : PatientListViewBinder(itemView) {

    val name: TextView = itemView.findViewById(R.id.tv_patientListRequest_name)
    val age: TextView = itemView.findViewById(R.id.tv_patientListRequest_age)

    override fun bind(model: PatientListModel) {
        name.text = model.name
        age.text = model.age
    }
}

abstract class PatientListViewBinder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(model: PatientListModel)
}

class PatientListItemFactory(val viewType: Int) {
    companion object {
        fun getViewHolder(type: Int, itemView: View): PatientListViewBinder? {
            return when (type) {
                PatientListModel.VIEWTYPE_HEADER -> PatientListHeaderViewHolder(itemView)
                PatientListModel.VIEWTYPE_PATIENT -> PatientListViewHolder(itemView)
                PatientListModel.VIEWTYPE_REQUEST -> PatientListRequestViewHolder(itemView)
                else -> null
            }
        }
    }
}



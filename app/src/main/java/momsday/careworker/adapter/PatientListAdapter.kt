package momsday.careworker.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.JsonObject

import java.util.ArrayList

import momsday.careworker.model.PatientListModel
import momsday.careworker.R
import momsday.careworker.connecter.Connect
import momsday.careworker.model.PatientResponseModel
import momsday.careworker.ui.patientInfo.PatientInfoActivity
import momsday.careworker.util.SingleLiveEvent
import momsday.careworker.util.getToken
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientListAdapter(private val models: ArrayList<PatientListModel>, val event: SingleLiveEvent<Any>) : RecyclerView.Adapter<PatientListViewBinder>() {

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

    override fun onBindViewHolder(holder: PatientListViewBinder, position: Int) = holder.bind(models[position], event)

    override fun getItemCount() = models.size

    override fun getItemViewType(position: Int) = models[position].viewType
}

class PatientListViewHolder(itemView: View) : PatientListViewBinder(itemView) {

    var name: TextView = itemView.findViewById(R.id.tv_patientListItem_name)
    var info: TextView = itemView.findViewById(R.id.tv_patientListItem_age)
    var protectorName: TextView = itemView.findViewById(R.id.tv_patientListItem_protector)

    override fun bind(model: PatientListModel, event: SingleLiveEvent<Any>) {
        name.text = model.name
        protectorName.text = model.protectorName
        info.text = model.info
        itemView.setOnClickListener { v ->
            val intent = Intent(itemView.context, PatientInfoActivity::class.java)
            intent.putExtra("id", model.id)
            intent.putExtra("name", model.name)
            itemView.context.startActivity(intent)
        }
    }
}

class PatientListHeaderViewHolder(itemView: View) : PatientListViewBinder(itemView) {

    val listInfo: TextView = itemView.findViewById(R.id.tv_patientListItemHeader_info)

    override fun bind(model: PatientListModel, event: SingleLiveEvent<Any>) {
        listInfo.text = model.name
    }
}

class PatientListRequestViewHolder(itemView: View) : PatientListViewBinder(itemView) {

    val name: TextView = itemView.findViewById(R.id.tv_patientListRequest_name)
    val age: TextView = itemView.findViewById(R.id.tv_patientListRequest_age)
    val accept: Button = itemView.findViewById(R.id.btn_patientListRequest_accept)

    override fun bind(model: PatientListModel, event: SingleLiveEvent<Any>) {
        name.text = model.name
        age.text = model.age
        accept.onClick {
            val jsonObject = JsonObject()
            jsonObject.addProperty("id", model.requestId)
            jsonObject.addProperty("reqId", model.id)
            jsonObject.addProperty("accept", true)
            Connect.getAPI().acceptRequest(getToken(itemView.context, true), jsonObject).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    Toast.makeText(itemView.context, "${response!!.code()}", Toast.LENGTH_SHORT).show()
                    event.call()
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    Toast.makeText(itemView.context, "안됨", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

abstract class PatientListViewBinder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: PatientListModel, event: SingleLiveEvent<Any>)
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



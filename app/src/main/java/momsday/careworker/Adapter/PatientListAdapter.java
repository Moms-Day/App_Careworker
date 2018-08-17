package momsday.careworker.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import momsday.careworker.Model.PatientListModel;
import momsday.careworker.Model.RequestListModel;
import momsday.careworker.R;
import momsday.careworker.ui.PatientInfo.PatientInfoActivity;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder> {

    private ArrayList<PatientListModel> models;

    public PatientListAdapter(ArrayList<PatientListModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public PatientListAdapter.PatientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_patient_list, parent, false);
        return new PatientListAdapter.PatientListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListAdapter.PatientListViewHolder holder, int position) {
        PatientListModel model = models.get(position);
        holder.itemView.setOnClickListener((v) -> {
            Intent intent = new Intent(holder.itemView.getContext(), PatientInfoActivity.class);
//            intent.putExtra("")
            holder.itemView.getContext().startActivity(intent);
        });
        holder.name.setText(model.getName());
        holder.protectorName.setText(model.getProtectorName());
        holder.info.setText(model.getInfo());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class PatientListViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView info;
        TextView protectorName;

        PatientListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_patientListItem_name);
            info = itemView.findViewById(R.id.tv_patientListItem_age);
            protectorName = itemView.findViewById(R.id.tv_patientListItem_protector);
        }
    }
}


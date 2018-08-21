package momsday.careworker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import momsday.careworker.model.RequestListModel;
import momsday.careworker.R;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.RequestListViewHolder> {

    private ArrayList<RequestListModel> models;

    public RequestListAdapter(ArrayList<RequestListModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public RequestListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_connect_request_list, parent, false);
        return new RequestListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestListViewHolder holder, int position) {
        RequestListModel model = models.get(position);

        holder.itemView.setOnClickListener((v) -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("요청 정보")
                    .setMessage("" + model.getName())
                    .setPositiveButton("확인", (dialog, which) -> {

                    })
                    .setNegativeButton("닫기", (dailog, which) -> {

                    })
                    .show();
        });
//        itemView.setOnClickListener((v) -> {
//        });
        holder.name.setText(model.getName());
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    class RequestListViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        RequestListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_conReqItem_name);
        }

    }
}


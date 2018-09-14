package momsday.careworker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import momsday.careworker.R;
import momsday.careworker.model.MainRecyclerChatItem;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_DATE = 0;
    public static final int VIEW_TYPE_ME = 1;
    public static final int VIEW_TYPE_YOU = 2;

    private ArrayList<MainRecyclerChatItem> mainRecyclerChatItems;

    public ChatRecyclerViewAdapter(ArrayList itemList) {
        mainRecyclerChatItems = itemList;
    }


    @Override
    public int getItemViewType(int position) {
        if (mainRecyclerChatItems.get(position).getItemViewType() == 0) {
            return VIEW_TYPE_DATE;
        } else if (mainRecyclerChatItems.get(position).getItemViewType() == 1) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_YOU;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_DATE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_chat_date, parent, false);
            return new MainChatRecyclerDateViewHolder(v);
        } else if (viewType == VIEW_TYPE_YOU) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_chat_me, parent, false);
            return new MainChatRecyclerMeViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_chat_you, parent, false);
            return new MainChatRecyclerYouViewHolder(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainRecyclerChatItem mainRecyclerChatItem = mainRecyclerChatItems.get(position);
        if (holder instanceof MainChatRecyclerDateViewHolder) {
            ((MainChatRecyclerDateViewHolder) holder).chatDateText.setText(mainRecyclerChatItems.get(position).dateText);

        } else if (holder instanceof MainChatRecyclerMeViewHolder) {
            ((MainChatRecyclerMeViewHolder) holder).chatMessageText.setText(mainRecyclerChatItems.get(position).messageText);
            ((MainChatRecyclerMeViewHolder) holder).chatTimeText.setText(mainRecyclerChatItems.get(position).timeText);
        } else {
            ((MainChatRecyclerYouViewHolder) holder).chatMessageText.setText(mainRecyclerChatItems.get(position).messageText);
            ((MainChatRecyclerYouViewHolder) holder).chatTimeText.setText(mainRecyclerChatItems.get(position).timeText);
        }
    }

    @Override
    public int getItemCount() {
        return mainRecyclerChatItems.size();
    }
}

class MainChatRecyclerDateViewHolder extends RecyclerView.ViewHolder {
    public TextView chatDateText;

    MainChatRecyclerDateViewHolder(View itemView) {
        super(itemView);
        chatDateText = (TextView)itemView.findViewById(R.id.chat_date);
    }
}

class MainChatRecyclerMeViewHolder extends RecyclerView.ViewHolder {
    public TextView chatMessageText, chatTimeText;

    public MainChatRecyclerMeViewHolder(View itemView) {
        super(itemView);

        chatMessageText = (TextView)itemView.findViewById(R.id.chat_my_message);
        chatTimeText = (TextView)itemView.findViewById(R.id.chat_my_time);
    }
}

class MainChatRecyclerYouViewHolder extends RecyclerView.ViewHolder{
    public TextView chatMessageText, chatTimeText;

    public MainChatRecyclerYouViewHolder(View itemView) {
        super(itemView);
        chatMessageText = (TextView)itemView.findViewById(R.id.chat_your_message);
        chatTimeText = (TextView)itemView.findViewById(R.id.chat_your_time);
    }
}

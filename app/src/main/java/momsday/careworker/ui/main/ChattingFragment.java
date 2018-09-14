package momsday.careworker.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import momsday.careworker.R;
import momsday.careworker.adapter.MainRecyclerChatListItem;
import momsday.careworker.ui.ChatActivity;

public class ChattingFragment extends Fragment {

    View view;
    RecyclerView mainChatListRecycler;
    LinearLayoutManager mainChatListLayoutManager;
    MainChatListRecyclerViewAdapter mainChatListRecyclerAdapter;

    public ChattingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chatting, container, false);
        mainChatListRecycler = view.findViewById(R.id.recycler_main_chat_list);
        mainChatListLayoutManager = new LinearLayoutManager(getContext());
        mainChatListLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ArrayList<MainRecyclerChatListItem> mainRecyclerChatListItems = new ArrayList();

        mainRecyclerChatListItems.add(new MainRecyclerChatListItem("qw", "Chatting"));
        mainChatListRecycler.setLayoutManager(mainChatListLayoutManager);
        mainChatListRecycler.setItemAnimator(new DefaultItemAnimator());

        mainChatListRecyclerAdapter = new MainChatListRecyclerViewAdapter(mainRecyclerChatListItems);
        mainChatListRecycler.setAdapter(mainChatListRecyclerAdapter);
        return view;
    }


    private class MainChatListRecyclerViewAdapter extends RecyclerView.Adapter<MainChatListRecyclerViewAdapter.MainChatListRecyclerViewHolder> {
        private ArrayList<MainRecyclerChatListItem> mainRecyclerChatListItems;
        Context mainRecyclerChatListContext;

        public MainChatListRecyclerViewAdapter(ArrayList itemList) {
            mainRecyclerChatListItems = itemList;
        }

        @NonNull
        @Override
        public MainChatListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_chat_list, parent, false);

            mainRecyclerChatListContext = parent.getContext();
            MainChatListRecyclerViewHolder holder = new MainChatListRecyclerViewHolder(v);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ChatActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MainChatListRecyclerViewHolder holder, int position) {
            holder.chatListNameText.setText(mainRecyclerChatListItems.get(position).nameText);
            holder.chatListMessageText.setText(mainRecyclerChatListItems.get(position).messageText);
        }


        @Override
        public int getItemCount() {
            return mainRecyclerChatListItems.size();
        }

        class MainChatListRecyclerViewHolder extends RecyclerView.ViewHolder {
            public TextView chatListNameText, chatListMessageText;
            public ImageView chatListProfileImage;

            public MainChatListRecyclerViewHolder(View itemView) {
                super(itemView);

                chatListNameText = (TextView) itemView.findViewById(R.id.text_chat_list_name);
                chatListMessageText = (TextView) itemView.findViewById(R.id.text_chat_list_message);
                chatListProfileImage = (ImageView) itemView.findViewById(R.id.image_chat_list_profile);
            }
        }
    }
}

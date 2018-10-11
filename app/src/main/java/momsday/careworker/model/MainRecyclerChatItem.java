package momsday.careworker.model;

import java.util.Date;

public class MainRecyclerChatItem {
    public String messageText;
    public String timeText;
    public String dateText;
    public Date date;
    private int itemViewType;

    public String getMessageText() {
        return messageText;
    }

    public String getTimeText() {
        return timeText;
    }

    public String getDateText() {
        return dateText;
    }

    public int getItemViewType() {
        return itemViewType;
    }

    public MainRecyclerChatItem() {
    }


    public MainRecyclerChatItem(int itemViewType, String messageText, String timeText, Date date) {
        this.itemViewType = itemViewType;
        this.messageText = messageText;
        this.timeText = timeText;
        this.date = date;
    }

    public MainRecyclerChatItem(int itemViewType, String dateText) {
        this.itemViewType = itemViewType;
        this.dateText = dateText;
    }
}

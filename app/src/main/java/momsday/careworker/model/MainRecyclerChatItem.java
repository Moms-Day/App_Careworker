package momsday.careworker.model;

public class MainRecyclerChatItem {
    public String  messageText;
    public String timeText;
    public String dateText;
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
  public   MainRecyclerChatItem() { }


    public MainRecyclerChatItem(int itemViewType, String messageText, String timeText) {
        this.itemViewType = itemViewType;
        this.messageText = messageText;
        this.timeText = timeText;
    }

    public MainRecyclerChatItem(int itemViewType, String dateText) {
        this.itemViewType = itemViewType;
        this.dateText = dateText;
    }
}

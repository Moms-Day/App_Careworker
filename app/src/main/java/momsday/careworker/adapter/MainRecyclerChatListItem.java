package momsday.careworker.adapter;

import android.media.Image;

public class MainRecyclerChatListItem {
    public String nameText;
    public String messageText;
    String id;
    Image profileImage;

    public String getNameText() {
        return nameText;
    }
    public String getMessageText() {
        return messageText;
    }
    public Image getProfileImage() {
        return profileImage;
    }

    public String getId() {
        return id;
    }

    public MainRecyclerChatListItem(String nameText, String messageText, String id) {
        this.nameText = nameText;
        this.messageText = messageText;
        this.id = id;
    }

}

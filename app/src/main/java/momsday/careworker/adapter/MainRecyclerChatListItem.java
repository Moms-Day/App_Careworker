package momsday.careworker.adapter;

import android.media.Image;

public class MainRecyclerChatListItem {
    public String nameText;
    public String messageText;
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

    public MainRecyclerChatListItem(String nameText, String messageText) {
        this.nameText = nameText;
        this.messageText = messageText;
    }

}

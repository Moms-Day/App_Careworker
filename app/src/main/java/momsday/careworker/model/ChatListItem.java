package momsday.careworker.model;

import android.media.Image;

public class ChatListItem {
    String nameText, messageText;
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

    public ChatListItem(String nameText, String messageText) {
        this.nameText = nameText;
        this.messageText = messageText;
    }
}

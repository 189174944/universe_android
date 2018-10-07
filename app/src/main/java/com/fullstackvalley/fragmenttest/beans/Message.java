package com.fullstackvalley.fragmenttest.beans;

import java.util.List;

public class Message {
    private String avatar;
    private String nickname;
    private List<MessageItem> messageItems;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<MessageItem> getMessageItems() {
        return messageItems;
    }

    public void setMessageItems(List<MessageItem> messageItems) {
        this.messageItems = messageItems;
    }

    public static class MessageItem{
        String time;
        String content;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

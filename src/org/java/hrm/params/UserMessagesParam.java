package org.java.hrm.params;

import java.io.Serializable;

public class UserMessagesParam implements Serializable {
    private String username;
    private int messageId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "UserMessagesParam{" +
                "username='" + username + '\'' +
                ", messageId=" + messageId +
                '}';
    }
}

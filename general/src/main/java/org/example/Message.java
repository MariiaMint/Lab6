package org.example;

import java.io.Serializable;

public class Message implements Serializable {
    String messageName;

    public Message(String commandName) {
        this.messageName = commandName;
    }

    public String getMessageName() {
        return messageName;
    }
}

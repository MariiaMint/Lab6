package org.example;

import java.io.Serializable;

public class MessWithArg extends Message {
    String arg;

    public MessWithArg(String messageName, String arg) {
        super(messageName);
        this.arg = arg;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getArg() {
        return arg;
    }

}

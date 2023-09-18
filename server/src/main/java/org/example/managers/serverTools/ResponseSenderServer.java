package org.example.managers.serverTools;

import org.example.MessWithHuman;
import org.example.Message;
import org.example.beginningClasses.HumanBeing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.example.tools.Printer.print;
import static org.example.managers.serverTools.ServerConnect.socket;

public class ResponseSenderServer {

    public static void send(String response) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message resp = new Message(response);
            oos.writeObject(resp);
            oos.flush();

            print("response was sent");
        } catch (IOException e) {
            print("ioex responseSender");
        }
    }
    public static void sendHuman(HumanBeing human) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MessWithHuman resp = new MessWithHuman("update", human);
            oos.writeObject(resp);
            oos.flush();

            print("response was sent");
        } catch (IOException e) {
            print("ioex responseSender");
        }
    }
}


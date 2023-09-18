package org.example;

import org.example.beginningClasses.HumanBeing;
import org.example.managers.CommandExecutor;
import org.example.managers.CommandManager;
import org.example.managers.serverTools.CsvToVector;
import org.example.managers.serverTools.RequestReaderServer;
import org.example.managers.serverTools.ServerConnect;

import java.io.IOException;
import java.util.Vector;

import static org.example.managers.serverTools.ServerConnect.*;
import static org.example.tools.Inputer.scanner;
import static org.example.tools.Printer.print;

/**
 * Главный класс сервера
 */
public class ServerMain {
    /**
     * Главный метод, который запускает сервер.
     * @param args Аргументы командной строки не используются
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Vector<HumanBeing> collection = new Vector<>();
        String env = "CSV";
        String csvFile = "";
        try {
            csvFile = System.getenv(env);
        } catch (NullPointerException e) {
            print("Переменная окружения CSV задана не корректно, задайте ее и запустите программу заново");
        }


        //парсер csv в Vector
        CsvToVector.csvToVector(csvFile, collection, scanner);


        CommandExecutor commandExecutor = new CommandExecutor(collection, csvFile, scanner);
        CommandManager commandManager = new CommandManager(commandExecutor);
        connect();


        while (true) {
            socket = ServerConnect.serverSocket.accept();
            print("connection accepted");
            while (!socket.isClosed()) {
                try {
                    Message recievedCommand = RequestReaderServer.read();
                    String argument = null;
                    HumanBeing human = null;

                    if (recievedCommand != null) {
                        String commandName = recievedCommand.getMessageName();
                        if (recievedCommand instanceof MessWithArg) {
                            argument = ((MessWithArg) recievedCommand).getArg();
                        }
                        if (recievedCommand instanceof MessWithHuman) {
                            human = ((MessWithHuman) recievedCommand).getHuman();
                        }
                        if (argument == null && human == null) {
                            commandManager.execute(commandName, "");
                        } else if (argument != null) {
                            commandManager.execute(commandName, argument);
                        } else {
                            commandManager.execute(commandName, human);
                        }
                    }
                } catch (RuntimeException e){
                    print("клиент отключился");
                    close();
                }
            }
        }
    }
}

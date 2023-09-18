package org.example.tools;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class ReadConsole {
    static String command;
    public static String read(Scanner scanner){
        System.out.println("Введите команду (чтобы увидеть справку по командам введите help)");
        try {
            command = scanner.nextLine();
        }catch (NoSuchElementException e){
            System.out.println("вы нажали ctrl D, подключитесь к серверу заново");
            System.exit(0);
        }
        return command;
    }
}

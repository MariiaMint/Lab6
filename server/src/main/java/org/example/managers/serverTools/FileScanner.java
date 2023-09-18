package org.example.managers.serverTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.tools.Printer.print;
import static org.example.managers.serverTools.ResponseSenderServer.send;

public class FileScanner {
    public static ArrayList<String> scan(String filepath){
        ArrayList<String> script = new ArrayList<>();
        try{
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String comm = scanner.nextLine();
                script.add(comm);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            send("Файл не найден");
        }
        return script;
    }
}

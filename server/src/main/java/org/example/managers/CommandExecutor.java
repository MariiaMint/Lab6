package org.example.managers;

import org.example.beginningClasses.*;
import org.example.tools.HumanComparator;
import org.example.comands.Command;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import static org.example.tools.Printer.print;
import static org.example.managers.serverTools.FileScanner.scan;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.String.valueOf;
import static org.example.managers.CommandManager.execute;
import static org.example.managers.serverTools.ResponseSenderServer.send;
import static org.example.managers.serverTools.ResponseSenderServer.sendHuman;

public class CommandExecutor {

    static Vector<HumanBeing> collection = new Vector<>();
    String csvFile;
    Scanner scanner;


    public CommandExecutor(Vector collection, String csvFile, Scanner scanner) {
        this.collection = collection;
        this.csvFile = csvFile;
        this.scanner = scanner;
    }

    //HELP
    public void help() {
        StringBuilder sb = new StringBuilder();
        for (Command element : CommandManager.getCommands().values()) {
            sb.append(element.description()).append("\n");
        }
        send(sb.toString());
    }

    // EXIT
    public void exit() {
        send("Завершаемся...");
        scanner.close();
    }

    //ADD
    public void add(HumanBeing human) {
        int id;
        if (collection.isEmpty()) {
            id = 0;
        } else {
            id = 0;
            for (HumanBeing person : collection) {
                id = max(id, person.getId() + 1);
            }
        }
        human.setId(id);
        LocalDateTime creationDate = LocalDateTime.now();
        human.setCreationDate(creationDate);
        collection.add(human);
        send("Human added");
    }


    //SAVE
    public void save() {
        try {
            FileWriter fw = new FileWriter(csvFile);
            StringBuilder sb = new StringBuilder();
            // Append strings from array
            for (HumanBeing element : collection) {
                sb.append(element.toCSV());
            }
            fw.write(sb.toString());
            fw.close();
            send("collection saved");

        } catch (IOException | NullPointerException e) {
            send("команда сохранить не может быть выполнена, проверьте, что у файла есть необходимые права доступа");
        }
    }

    //SHOW
    public void show() {
        StringBuilder response = new StringBuilder();
        response.append("id; name; coordinates; creationDate; realHero; hasToothpick; impactSpeed; weaponType; mood; carName; carCool\n");
        for (HumanBeing obj : collection) {
            response.append(obj.toString()).append("\n");
        }
        send(response.toString());
    }

    //remove_first
    public void removeFirst() {
        if (!collection.isEmpty()) {
            collection.removeElementAt(0);
            send("первый элемент удален");
        } else {
            send("коллекция итак пуста");
        }
    }

    //clear
    public void clear() {
        collection.removeAllElements();
        send("коллекция очищена");
    }

    //print_field_descending_mood
    public void printFieldDescendingMood() {
        StringBuilder sb = new StringBuilder();
        int apathy = 0;
        int sorrow = 0;
        int sadness = 0;
        for (HumanBeing obj : collection) {
            if (obj.getMood() == Mood.APATHY) {
                apathy += 1;
            } else if (obj.getMood() == Mood.SADNESS) {
                sadness += 1;
            } else if (obj.getMood() == Mood.SORROW) {
                sorrow += 1;
            }
        }
        for (int i = 0; i < sorrow; i++) {
            sb.append("SORROW\n");
        }
        for (int i = 0; i < sadness; i++) {
            sb.append("SADNESS\n");
        }
        for (int i = 0; i < apathy; i++) {
            sb.append("APATHY\n");
        }
        send(sb.toString());
    }

    //count_less_than_impact_speed impactSpeed
    public void countLessThanImpactSpeed(String strIs) {
        Double impactSpeed = parseDouble(strIs);
        int number = 0;
        for (HumanBeing obj : collection) {
            if (!(obj.getImpactSpeed() == null)) {
                if (obj.getImpactSpeed() < impactSpeed) {
                    number += 1;
                }
            }
        }
        send("количество элементов, значение поля impactSpeed которых меньше заданного равно " + number);
    }

    //removeBId id
    public void removeBId(String strId) {
        boolean removed = false;
        for (HumanBeing obj : collection) {
            if (obj.getId() == parseInt(strId)) {
                collection.removeElement(obj);
                removed = true;
                break;
            }
        }
        if (removed) {
            send("Человек удален");
        } else {
            send("Нет человека с таким id");
        }
    }

    //update id
    public void update(String strId) {
        boolean here = false;
        for (HumanBeing person : collection) {
            if (person.getId() == parseInt(strId)) {
                sendHuman(person);
                here = true;
            }
        }
        if (!here) {
            send("Человека с таким id нет");
        }
    }
    public void update(HumanBeing human) {
        removeBId(valueOf(human.getId()));
        collection.add(human.getId(),human);
        send("Человек обновлен");
        }

    //info
    public void info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Информация о коллекции:\n");
        sb.append("\tТип: Vector\n");
        sb.append("\tКласс объектов: HumanBeing\n");
        sb.append("\tКоличество элементов: " + collection.size() + "\n");
        if (!collection.isEmpty()) {
            sb.append("\tВремя инициализации: " + collection.firstElement().creationDateToString());
        }
        send(sb.toString());
    }

    //sort
    public void sort() {
        Collections.sort(collection);
        send("Коллекция отсортирована");
    }

    //print_descending
    public void printDescending() {
        StringBuilder sb = new StringBuilder();
        ArrayList<HumanBeing> list = new ArrayList<>(collection);
        list.sort(new HumanComparator());
        sb.append("id; name; coordinates; creationDate; realHero; hasToothpick; impactSpeed; weaponType; mood; carName; carCool\n");
        for (HumanBeing obj : list) {
            sb.append(obj.toString() + "\n");
        }
        send(sb.toString());
    }

    //add_if_max
    public void addIfMax(HumanBeing person) {
        print("сравнение идет пунктам realHero, coordinate X, Y, carCool");
        if (!collection.isEmpty()) {
            Vector<HumanBeing> vector = new Vector<>(collection);
            Collections.sort(vector, new HumanComparator());
            Vector<HumanBeing> vector2 = new Vector<>();
            vector2.add(person);
            vector2.add(vector.elementAt(0));
            Collections.sort(vector2, new HumanComparator());
            if (vector2.elementAt(0) == person) {
                collection.add(person);
            } else {
                send("Элемент не является максимальным, мы его не добавили");
            }
        } else {
            collection.add(person);
            send("добавлен");
        }
    }

    //execute_script
    public void execute_script(){
        send("файл пуст");
    }


}






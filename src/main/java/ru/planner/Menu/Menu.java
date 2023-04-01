package ru.planner.Menu;

import ru.planner.Chaecker.TaskHandler;
import ru.planner.FileManger.*;
import ru.planner.Repository.Repository;
import ru.planner.Scanner.ConsoleScanner;
import ru.planner.model.Priority;
import ru.planner.model.Task;

import java.sql.Date;
import java.util.ArrayList;

public class Menu {

    private Converter<FormatCSV> converterCSV;
    private Converter<FormatJSON> converterJSON;
    private Converter<FormatXML> converterXML;
    private FileManger fileManger;
    private Repository repository;

    public void startMenu() {
        do {
            System.out.println("Input choice:\n" +
                    "1 - read file\n" +
                    "2 - save to file\n" +
                    "3 - add task\n" +
                    "0 - exit");
            String data = ConsoleScanner.read();
            switch (data) {
                case "1":
                    readFile();
                case "2":
                    saveFile();
                case "3":
                    addTask();
                case "0":
                    break;
            }
        } while (true);

    }

    private void addTask() {
        do {
            System.out.println("To exit input 0.\nInput data format:\n" +
                    "title deadline(format: dd.MM.yyyy) priority(1 - LOW, 2 - HIGH, 3 - MEDIUM)");
            String data = ConsoleScanner.read();
            if (data.equals("0")) {
                break;
            }
            try {
                repository.save(TaskHandler.dataHandler(data));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }

    private void saveFile() {
        do {
            System.out.println("Input type file:\n" +
                    "1 - csv\n" +
                    "2 - json\n" +
                    "3 - xml\n" +
                    "0 - exit");
            String data = ConsoleScanner.read();
            String name = "";
            switch (data) {
                case "1":
                    name = getNameOfFile();
                    fileManger.writeFile(converterCSV.getFormatData(repository.getAllTasks()), name, true);
                    break;
                case "2":
                    name = getNameOfFile();
                    fileManger.writeFile(converterJSON.getFormatData(repository.getAllTasks()), name, true);
                    break;
                case "3":
                    name = getNameOfFile();
                    fileManger.writeFile(converterXML.getFormatData(repository.getAllTasks()), name, true);
                    break;
                case "0":
                    break;
            }
        } while (true);

    }

    private String getNameOfFile() {
        do {
            System.out.println("Input name file. 0 - exit");
            String data = ConsoleScanner.read();
            if (data.equals("0")) {
                break;
            }
            if (!data.equals("")) {
                return data;
            }
        } while (true);
        return "";
    }

    private void readFile() {
        do {
            repository = Repository.getInstance();
            System.out.println("Input name file in home path. Home path is: " + System.getProperty("user.home"));
            String data = ConsoleScanner.read();
            if (data.equals("0")) {
                break;
            }
            String[] nameArray = data.split("\\.");
            if (nameArray.length == 2 &&
                    (nameArray[1].equals("csv")) || nameArray[1].equals("json") || nameArray[1].equals("xml")) {
                switch (nameArray[1]) {
                    case ("csv"):
                        repository.saveAll(converterCSV.upload(fileManger.readFile(data)));
                    case ("json"):
                        repository.saveAll(converterJSON.upload(fileManger.readFile(data)));
                    case ("xml"):
                        repository.saveAll(converterXML.upload(fileManger.readFile(data)));
                    default:
                        new ArrayList<Task>();
                }
                break;
            } else {
                System.err.println("incorrect file type or name. Try again");
            }
        } while (true);
    }

}

package ru.planner.Chaecker;

import ru.planner.model.Priority;
import ru.planner.model.Task;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TaskHandler {
    public static Task dataHandler(String data) {
        Task task = startDataHandler(data);
        String errLog = "";
        if (task.getTitle().equals("")) {
            errLog += "title is empty; ";
        }
        if (task.getDeadline().equals("")) {
            errLog += "deadline is empty; ";
        }
        if (task.getPriority().equals("")) {
            errLog += "Priority is empty; ";
        }
        if (!errLog.equals("")) {
            throw new RuntimeException(errLog);
        }
        return task;
    }

    private static Task startDataHandler(String data) {
        String[] arrayTask = data.split(" ");
        return new Task(arrayTask[0], toDate(arrayTask[1]), toPriority(arrayTask[2]));
    }

    private static Priority toPriority(String data) {
        switch (data) {
            case "1":
                return Priority.LOW;
            case "2":
                return Priority.MEDIUM;
            case "3":
                return Priority.HIGH;
        }
        return Priority.UNSET;
    }

    private static Date toDate(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
        myFormat.setLenient(false);
        try {
            return (Date) myFormat.parse(date);
        } catch (Exception e) {
            return new Date(0L);
        }
    }
}

package ru.planner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@AllArgsConstructor
@Data
public class Task {
    protected int id;
    protected String title;
    protected String nameAuthor;
    protected Date dateAdd;
    protected Date deadline;
    protected Priority priority;

    public Task(String title, Date deadline, Priority priority) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
    }

    public static Task getNullTask(){
        return new Task(-1, "", "", new Date(0L), new Date(0L), Priority.MEDIUM);
    }
}

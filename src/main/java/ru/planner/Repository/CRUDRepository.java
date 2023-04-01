package ru.planner.Repository;

import ru.planner.model.Task;

import java.util.ArrayList;

public interface CRUDRepository {
    void save(Task task);
    ArrayList<Task> getAllTasks();

}

package ru.planner.FileManger;

import ru.planner.model.Task;

import java.util.ArrayList;

public interface Formatting {
    String save(ArrayList<Task> data);

    ArrayList<Task> unload(String data);
}

package ru.planner.FileManger;

import ru.planner.model.Task;

import java.util.ArrayList;

public class Converter<T extends Formatting> {
    protected T type;

    public String getFormatData(ArrayList<Task> data) {
        if (data != null) {
            return type.save(data);
        }
        return "";
    }

    public ArrayList<Task> upload(String data) {
        return type.unload(data);
    }
}

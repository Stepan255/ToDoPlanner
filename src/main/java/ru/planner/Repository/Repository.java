package ru.planner.Repository;

import ru.planner.model.Task;

import java.util.ArrayList;

public class Repository implements CRUDRepository {
    protected ArrayList<Task> tasksRepository;
    private static Repository repository;


    private Repository(ArrayList<Task> tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public static Repository getInstance() {
        if (repository == null) {
            return new Repository(new ArrayList<>());
        }
        return repository;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return tasksRepository;
    }

    @Override
    public void save(Task task) {
        tasksRepository.add(task);
    }

    public void saveAll(ArrayList<Task> tasks) {
        for (Task task :
                tasks) {
            save(task);
        }
    }


}

package ru.planner.Chaecker;

public class DataHandler<T extends ProcessData<E>, E> {
    private T type;

    public E correspondsToDataFormat(String data) {
        return (E) type.correspondsToDataFormat(data);
    }

}

package ru.planner.Chaecker;

public class LocationHandler implements ProcessData<Coordinate>, LocationProcessing{

    @Override
    public Coordinate correspondsToDataFormat(String data) {
        return new Coordinate(10.0, 10.0);
    }

    @Override
    public String getAddress(String name) {
        return "address"; //TODO do api
    }
}

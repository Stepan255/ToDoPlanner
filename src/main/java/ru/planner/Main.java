package ru.planner;

import ru.planner.Chaecker.DataHandler;
import ru.planner.FileManger.FileManger;
import ru.planner.Menu.Menu;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) {
        new Menu().startMenu();
    }
}
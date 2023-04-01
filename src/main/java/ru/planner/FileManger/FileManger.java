package ru.planner.FileManger;

import java.io.*;

public class FileManger {
    final private String SEP = System.getProperty("file.separator");
    final public static String SEPARATOR = System.getProperty("file.separator");
    final private String ROOT_PATH = System.getProperty("user.home") + SEP;;
    private String path;
    public FileManger() {
        this.path = getRootPath();
    }

    public String getSEP() {
        return SEP;
    }

    public String getRootPath() {
        return ROOT_PATH;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String readFile(String name) {
        FileReader file = tryOpenFileToRead(name);
        StringBuilder rawData = new StringBuilder();
        if (file != null) {
            int c;
            try {
                while ((c = file.read()) != -1) {
                    char ch = (char) c;
                    rawData.append(ch);
                }
                file.close();
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода при чтении");
            }
        } else {
            System.out.println("Ошибка при открытии файла для чтения");
        }
        return rawData.toString();
    }

    public void writeFile(String data, String name, Boolean append) {
        FileWriter file = tryFileToRead(name, append);
        if (file != null) {
            try {
                for (int i = 0; i < data.length(); i++) {
                    file.append(data.charAt(i));
                }
            } catch (IOException e) {
                System.out.println("Ошибка при добавлении записи");
            } finally {
                try {
                    file.close();
                } catch (IOException e) {
                    System.out.println("Ошибка при закрытии");
                }
            }
        } else {
            System.out.println("Ошибка при открытии файла для записи");
        }
    }

    public void writeFile(String data, String name) {
        writeFile(data, name, true);
    }

    private FileReader tryOpenFileToRead(String name) {
        String pathFile = this.path + name;
        try {
            FileReader file = new FileReader(pathFile);
            if (file.ready()) {
                return file;
            } else {
                System.out.println("файл: " + pathFile + " - не готов к чтению");
            }
        } catch (FileNotFoundException e) {
            System.out.println("файл с путем:" + pathFile + "не существует");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода при проверке на чтение");
        }
        return null;
    }

    private FileWriter tryFileToRead(String name, Boolean append) {
        if (doesTheFileExist(name)) {
            try {
                String pathFile = this.path + name;
                return new FileWriter(pathFile, append);
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода при проверке на чтение");
            }
        }
        return null;
    }

    private Boolean doesTheFileExist(String name) {
        String pathFile = this.path + name;
        File file = new File(pathFile);
        try {
            if (file.createNewFile())
                System.out.println("файл '" + pathFile + "' создан");
            return true;
        } catch (Exception e) {
            System.out.println("ошибка при создании файла: '" + pathFile + "'");
        }
        return false;
    }
}

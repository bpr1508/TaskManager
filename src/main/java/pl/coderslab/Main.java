package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static pl.coderslab.ConsoleColors.*;

public class Main {
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks = new String[0][];

    public static void main(String[] args) throws FileNotFoundException {
        fileToArray();
        int i = 0;
        String option = "";
        while (!"exit".equals(option)) {
            meeting();
            option = chooseOption();
            if ("add".equals(option)) {
                addTask();
            } else if ("remove".equals(option)) {
                removeTask();
            } else if ("list".equals(option)) {
                listTasks();
            } else {
                writeToFile();
            }
        }
    }

    private static void meeting() {
        System.out.println(BLUE + "Please select an option: " + RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    private static void fileToArray() throws FileNotFoundException {
        File file = new File("tasks.csv");
        StringBuilder reading = new StringBuilder();
        if (!file.exists()) {
            throw new FileNotFoundException("Brak pliku.");
        }
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            reading.append(scan.nextLine() + ";");
        }
        String[] lines = reading.toString().split(";");
        for (String line : lines) {
            String[] task = line.split(",");
            tasks = ArrayUtils.add(tasks, task);
        }
        System.out.println(PURPLE + tasks.length + " tasks have bean read" + RESET);
    }

    private static void listTasks() {

    }

    private static void writeToFile() {
    }

    private static void removeTask() {

    }

    private static void addTask() {
    }

    private static String chooseOption() {
        String option = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextLine();
            switch (option) {
                case "add":
                    return "add";
                case "remove":
                    return "remove";
                case "list":
                    return "list";
                case "exit":
                    return "exit";
                default:
                    System.out.println("Please select a correct option.");
            }
        }
    }
}
package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static pl.coderslab.ConsoleColors.*;

public class Main {
    static String[][] tasks = new String[0][];

    public static void main(String[] args) throws FileNotFoundException {
        fileToArray();
        String option = "";
        while (!"exit".equals(option)) {
            meeting();
            option = chooseOption();
            switch (option) {
                case "add" -> addTask();
                case "remove" -> removeTask();
                case "list" -> listTasks();
                default -> writeToFile();
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
            reading.append(scan.nextLine()).append(";");
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
        if (tasks.length == 0) {
            System.out.println(RED + "No tasks to remove." + RESET);
            return;
        }
        System.out.print(BLUE + "Please select index to remove: " + RESET);
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.print(RED + "Invalid argument passed. Please give number between 0 and " + (tasks.length - 1) + ": " + RESET);
            }
            index = scanner.nextInt();
            if (index >= 0 && index < tasks.length) {
                break;
            } else {
                System.out.print(RED + "Invalid argument passed. Please give number between 0 and " + (tasks.length - 1) + ": " + RESET);
            }
        }
        String[] task = tasks[index];
        System.out.print(BLUE + "Please confirm (Y/y) to remove task '" + task[0] + "' > " + RESET);
        scanner = new Scanner(System.in);
        String confirmed = scanner.nextLine();
        if ("y".equalsIgnoreCase(confirmed)) {
            tasks = ArrayUtils.remove(tasks, index);
            System.out.println("Task was successfully deleted");
        }
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description: ");
        String description = scanner.nextLine();
        System.out.println("Please add task due date: ");
        String dueDate = scanner.nextLine();
        String important ="";
        System.out.println("Please add task is important - true/false: ");
        while(!"true".equals(important) && !"false".equals(important)){
            important = scanner.nextLine();
            System.out.println("Please add task is important - true/false: ");
        }
        tasks = ArrayUtils.add(tasks, new String[]{description, dueDate, important});
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
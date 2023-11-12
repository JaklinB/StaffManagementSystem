package ui;

import managers.StaffManager;

import java.util.Scanner;

public class Menu {
    private final StaffManager manager;
    private final Scanner scanner;
    private boolean isRunning;

    public Menu(StaffManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
        this.isRunning = true;
    }

    public void start() {
        System.out.println("\nAvailable Commands:");
        System.out.println("1 - Add Employee");
        System.out.println("2 - Edit Employee");
        System.out.println("3 - List Employees");
        System.out.println("4 - Search by Department");
        System.out.println("5 - Search by ID");
        System.out.println("6 - Search by Name");
        System.out.println("7 - Fire Employee");
        System.out.println("8 - Save & Exit");

        int command = readCommand();
        switch (command) {
            case 1 -> manager.addEmployee();
            case 2 -> manager.editEmployee();
            case 3 -> manager.listEmployees();
            case 4 -> manager.searchByDepartment();
            case 5 -> manager.searchById();
            case 6 -> manager.searchByName();
            case 7 -> manager.fireEmployee();
            case 8 -> {
                System.out.println("Exiting...");
                isRunning = false;
            }
            default -> System.out.println("Unknown command. Please try again.");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    private int readCommand() {
        System.out.print("Enter command number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print("Enter command number: ");
        }
        int command = scanner.nextInt();
        scanner.nextLine();
        return command;
    }

}

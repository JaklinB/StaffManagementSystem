package managers.helpers;

import enums.Department;
import models.Employee;
import services.Service;
import utils.Constants;

import java.util.List;
import java.util.Scanner;

public class EmployeeSearcher {

    public static void searchById(Service service, Scanner scanner) {
        System.out.println("\nEnter employee ID:");
        String idInput = scanner.nextLine();

        try {
            int id = Integer.parseInt(idInput.trim());
            Employee employee = service.searchEmployeeById(id);

            if (employee != null) {
                System.out.println("\nFound Employee: " + employee);
            } else {
                System.out.println("\nNo employee found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid number.");
        }
    }

    public static void searchByName(Service service, Scanner scanner) {
        System.out.println("\nEnter employee name:");
        String name = scanner.nextLine().trim();

        List<Employee> employees = service.searchEmployeesByName(name);

        if (!employees.isEmpty()) {
            System.out.println("\nFound Employees:");
            employees.forEach(System.out::println);
        } else {
            System.out.println("No employees found with the name: " + name);
        }
    }

    public static void searchByDepartment(Service service, Scanner scanner) {
        System.out.println("\nEnter department:");
        String departmentInput = scanner.nextLine();

        try {
            Department department = Department.valueOf(departmentInput.toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            List<Employee> employees = service.searchEmployeesByDepartment(department);

            if (!employees.isEmpty()) {
                System.out.println("\nFound Employees in " + department + " Department:");
                employees.forEach(System.out::println);
            } else {
                System.out.println("\nNo employees found in " + department + " Department.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid department. Please enter a valid department.");
        }
    }
}

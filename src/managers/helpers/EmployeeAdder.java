package managers.helpers;

import enums.Department;
import enums.Role;
import models.Employee;
import services.Service;
import utils.DateUtils;
import utils.Constants;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeAdder {

    public static void addEmployee(Service service, Scanner scanner) {
        System.out.println("\nEnter employee details");
        System.out.println("ID, Name, Start Date (yyyy-MM-dd), Department, Role, Salary");
        String input = scanner.nextLine();
        String[] details = input.split(Constants.COMMA_DELIMITER);

        if (details.length != 6) {
            System.out.println("Invalid input. Please provide ID, Name, Start Date, Department, Role, Salary.");
            return;
        }

        try {
            int id = Integer.parseInt(details[0].trim());
            String name = details[1].trim();
            LocalDate startDate = DateUtils.parseDate(details[2].trim());
            Department department = Department.valueOf(details[3].trim().toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            Role role = Role.valueOf(details[4].trim().toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            double salary = Double.parseDouble(details[5].trim());

            Employee newEmployee = new Employee(id, name, startDate, null, department, role, salary);
            if (startDate != null) {
                service.addEmployee(newEmployee);
                System.out.println("Employee added successfully.");
            }


        } catch (NumberFormatException e) {
            System.out.println("Invalid number format." + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid data: " + e.getMessage());
        }
    }
}

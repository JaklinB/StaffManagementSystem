package managers.helpers;

import enums.Department;
import enums.Role;
import models.Employee;
import services.Service;
import utils.DateUtils;
import utils.Constants;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeEditor {

    public static void editEmployee(Service service, Scanner scanner) {
        System.out.println("\nEnter the ID of the employee to edit, followed by new details:");
        String input = scanner.nextLine();
        String[] details = input.split(Constants.COMMA_DELIMITER);

        if (details.length != 6) {
            System.out.println("Invalid input. Please provide ID, Name, Start Date, Department, Role, Salary.");
            return;
        }

        try {
            int id = Integer.parseInt(details[0].trim());
            Optional<Employee> existingEmployee = Optional.ofNullable(service.searchEmployeeById(id));

            String name = details[1].trim();
            LocalDate startDate = DateUtils.parseDate(details[2].trim());
            Department department = Department.valueOf(details[3].trim().toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            Role role = Role.valueOf(details[4].trim().toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            double salary = Double.parseDouble(details[5].trim());

            LocalDate endDate = existingEmployee.map(Employee::getEndDate).orElse(null);

            Employee updatedEmployee = new Employee(id, name, startDate, endDate, department, role, salary);
            service.editEmployee(id, updatedEmployee);
            System.out.println("Employee details updated successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please ensure ID and Salary are numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid department, role, or date format. Please ensure they are correct.");
        }
    }
}

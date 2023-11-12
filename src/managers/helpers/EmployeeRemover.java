package managers.helpers;

import models.Employee;
import services.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeRemover {

    public static void removeEmployee(Service service, Scanner scanner) {
        System.out.println("\nEnter employee ID to fire:");
        String idInput = scanner.nextLine();

        try {
            int id = Integer.parseInt(idInput.trim());
            Optional<Employee> employee = Optional.ofNullable(service.searchEmployeeById(id));

            if (employee.isPresent()) {
                Employee updatedEmployee = employee.get();
                updatedEmployee.setEndDate(LocalDate.now());
                service.editEmployee(id, updatedEmployee);
                System.out.println("Employee with ID " + id + " has been marked as fired.");
            } else {
                System.out.println("No employee found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid number.");
        }
    }
}

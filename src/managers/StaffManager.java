package managers;

import managers.helpers.*;
import services.Service;

import java.util.Scanner;

public class StaffManager {
    private final Service service;

    private final Scanner scanner;

    public StaffManager(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void addEmployee() {
        EmployeeAdder.addEmployee(service, scanner);
    }

    public void editEmployee() {
        EmployeeEditor.editEmployee(service, scanner);
    }

    public void listEmployees() {
        EmployeeLister.listEmployees(service);
    }

    public void searchByDepartment() {
        EmployeeSearcher.searchByDepartment(service, scanner);
    }

    public void searchById() {
        EmployeeSearcher.searchById(service, scanner);
    }

    public void searchByName() {
        EmployeeSearcher.searchByName(service, scanner);
    }

    public void fireEmployee() {
        EmployeeRemover.removeEmployee(service, scanner);
    }
}

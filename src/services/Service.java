package services;

import enums.Department;
import models.Employee;

import java.util.List;

public interface Service {
    void addEmployee(Employee employee);

    void editEmployee(int id, Employee updatedEmployee);

    List<Employee> listEmployees();

    Employee searchEmployeeById(int id);

    List<Employee> searchEmployeesByDepartment(Department department);

    List<Employee> searchEmployeesByName(String name);
    
}


package services;

import models.Employee;
import enums.Department;
import enums.FileType;
import utils.Constants;
import utils.files.CSVUtil;
import utils.files.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StaffService implements Service {
    private List<Employee> employees;
    /**
     * @param fileType The file type for data storage (CSV or JSON).
     */
    private final FileType fileType;

    public StaffService(FileType fileType) {
        this.fileType = fileType;
        loadEmployees();
    }

    private void loadEmployees() {
        if (FileType.JSON.equals(fileType)) {
            this.employees = JSONUtil.readEmployeesFromJSON(Constants.JSON_FILE_PATH);
        } else {
            this.employees = CSVUtil.readEmployeesFromCSV(Constants.CSV_FILE_PATH);
        }
    }

    /**
     * Checks if an employee ID is unique in the list of employees.
     *
     * @param id The employee ID to check.
     * @return true if the ID is unique, false otherwise.
     */
    private boolean isUniqueId(int id) {
        return this.employees.stream().noneMatch(employee -> employee.getId() == id);
    }

    @Override
    public void addEmployee(Employee employee) {
        if (isUniqueId(employee.getId())) {
            this.employees.add(employee);
            saveEmployees();
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("An employee with ID " + employee.getId() + " already exists.");
        }
    }

    @Override
    public void editEmployee(int id, Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                employees.set(i, updatedEmployee);
                break;
            }
        }
        saveEmployees();
    }

    @Override
    public List<Employee> listEmployees() {
        return new ArrayList<>(employees);
    }


    @Override
    public Employee searchEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<Employee> searchEmployeesByDepartment(Department department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }


    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }


    private void saveEmployees() {
        if (FileType.JSON.equals(fileType)) {
            JSONUtil.writeEmployeesToJSON(Constants.JSON_FILE_PATH, employees);
        } else {
            CSVUtil.writeEmployeesToCSV(Constants.CSV_FILE_PATH, employees);
        }
    }
}

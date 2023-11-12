package utils.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.Employee;
import utils.ValidationUtils;
import enums.Department;
import enums.Role;
import utils.DateUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static List<Employee> readEmployeesFromJSON(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Employee.class);
            employees = mapper.readValue(reader, javaType);
            employees = validateEmployees(employees);
        } catch (IOException e) {
            System.out.println("Error reading from JSON file: " + filePath + ". Error: " + e.getMessage());
        }
        return employees;
    }

    private static List<Employee> validateEmployees(List<Employee> employees) {
        List<Employee> validEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (isValidEmployee(employee)) {
                validEmployees.add(employee);
            } else {
                System.out.println("Invalid employee data: " + employee);
            }
        }
        return validEmployees;
    }

    private static boolean isValidEmployee(Employee employee) {
        return ValidationUtils.isValidId(String.valueOf(employee.getId())) &&
                ValidationUtils.isNotNullOrEmpty(employee.getName()) &&
                DateUtils.isValidDate(employee.getStartDate().toString()) &&
                (employee.getEndDate() == null || DateUtils.isValidDate(employee.getEndDate().toString())) &&
                ValidationUtils.isValidEnum(Department.class, employee.getDepartment().name()) &&
                ValidationUtils.isValidEnum(Role.class, employee.getRole().name()) &&
                ValidationUtils.isValidSalary(String.valueOf(employee.getSalary()));
    }

    public static void writeEmployeesToJSON(String filePath, List<Employee> employees) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            mapper.writeValue(writer, employees);
        } catch (IOException e) {
            System.out.println("Error writing to JSON file: " + filePath + ". Error: " + e.getMessage());
        }
    }
}

package utils.files;

import enums.Department;
import enums.Role;
import models.Employee;
import utils.Constants;
import utils.DateUtils;
import utils.ValidationUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtil {
    /**
     * Reads employee data from a CSV file and creates a list of Employee objects.
     * Each line of the CSV is expected to contain exactly 7 fields, matching the Employee attributes.
     * If a line does not conform to the expected format or contains invalid data, it is skipped,
     * and a message is printed to the console indicating the line number and the problematic data.
     *
     * @param filePath The path to the CSV file.
     * @return A list of Employee objects read from the file.
     */
    public static List<Employee> readEmployeesFromCSV(String filePath) {
        List<Employee> employees = new ArrayList<>();
        int lineNumber = 1;
        int skippedLines = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(Constants.COMMA_DELIMITER);
                if (values.length != 7 || !isValidEmployeeData(values)) {
                    System.out.println("Invalid data at line " + lineNumber + ": " + line);
                    skippedLines++;
                    continue;
                }
                Employee employee = parseEmployee(values);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from CSV file: " + filePath + ". Error: " + e.getMessage());
        }

        if (skippedLines > 0) {
            System.out.println("Total skipped lines: " + skippedLines);
        }

        return employees;
    }


    private static boolean isValidEmployeeData(String[] values) {
        return ValidationUtils.isValidId(values[0]) &&
                ValidationUtils.isNotNullOrEmpty(values[1]) &&
                DateUtils.isValidDate(values[2]) &&
                (values[3].isEmpty() || DateUtils.isValidDate(values[3])) &&
                ValidationUtils.isValidEnum(Department.class, values[4]) &&
                ValidationUtils.isValidEnum(Role.class, values[5]) &&
                ValidationUtils.isValidSalary(values[6]);
    }


    private static Employee parseEmployee(String[] values) {
        try {
            int id = Integer.parseInt(values[0]);
            String name = values[1];
            LocalDate startDate = DateUtils.parseDate(values[2]);
            LocalDate endDate = values[3].isEmpty() ? null : DateUtils.parseDate(values[3]);
            Department department = Department.valueOf(values[4].toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            Role role = Role.valueOf(values[5].toUpperCase().replace(" ", Constants.ENUM_SPACE_REPLACEMENT));
            double salary = Double.parseDouble(values[6]);
            return new Employee(id, name, startDate, endDate, department, role, salary);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            System.out.println("Error parsing employee data: " + Arrays.toString(values) + ". Error: " + e.getMessage());
            return null;
        }
    }

    public static void writeEmployeesToCSV(String filePath, List<Employee> employees) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
            bw.write("Id,Name,StartDate,EndDate,Department,Role,Salary\n");
            for (Employee employee : employees) {
                String endDateStr = employee.getEndDate() != null ? DateUtils.formatDate(employee.getEndDate()) : "";
                String line = String.format("%d,%s,%s,%s,%s,%s,%.2f\n",
                        employee.getId(),
                        employee.getName(),
                        DateUtils.formatDate(employee.getStartDate()),
                        endDateStr,
                        employee.getDepartment().name(),
                        employee.getRole().name(),
                        employee.getSalary());
                bw.write(line);
            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + filePath + ". Error: " + e.getMessage());
        }
    }


}

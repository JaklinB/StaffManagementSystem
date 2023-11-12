package models;

import enums.Department;
import enums.Role;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Department department;
    private Role role;
    private double salary;

    /**
     * Parameterized constructor for creating an Employee object.
     * <p>
     * This constructor is used to create an Employee object with specific values,
     * ensuring that all necessary data is provided upon creation.
     *
     * @param id         The unique identifier for the employee.
     * @param name       The name of the employee.
     * @param startDate  Date of appointment of the employee.
     * @param endDate    The end date of the employee's employment;
     *                   This field is null for current employees.
     *                   It is set to a date when the employee is terminated or resigns.
     * @param department The department to which the employee belongs.
     * @param role       The role or position of the employee.
     * @param salary     The salary of the employee.
     */

    public Employee(int id, String name, LocalDate startDate, LocalDate endDate, Department department, Role role, double salary) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    /**
     * Default no-argument constructor for the Employee class.
     * <p>
     * Purpose:
     * - Used by JSON deserialization libraries (Jackson).
     * - Essential for frameworks that rely on reflection and require a public
     * default constructor for operations like serialization/deserialization.
     * <p>
     * Usage Note:
     * - This constructor should not be used for creating Employee instances within
     * the application logic, as it does not initialize the object with specific,
     * meaningful data. For creating Employee objects, the parameterized constructor
     * should be used which ensures all necessary fields are appropriately set.
     * <p>
     * Implementation:
     * - The constructor is intentionally left empty. It serves the purpose of
     * allowing class instantiation without setting any properties, which is
     * a prerequisite for certain automated operations like JSON deserialization.
     */
    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                (endDate != null ? ", endDate=" + endDate : "") +
                ", department=" + department +
                ", role=" + role +
                ", salary=" + salary +
                '}';
    }

}

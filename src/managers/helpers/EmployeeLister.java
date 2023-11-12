package managers.helpers;

import services.Service;

public class EmployeeLister {
    public static void listEmployees(Service service) {
        service.listEmployees().forEach(System.out::println);
    }
}

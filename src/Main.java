import enums.FileType;
import services.Service;
import services.StaffService;
import managers.StaffManager;
import ui.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new StaffService(FileType.CSV);
        Scanner scanner = new Scanner(System.in);
        StaffManager manager = new StaffManager(service, scanner);
        Menu menu = new Menu(manager, scanner);

        System.out.println("\nWelcome to Staff Management System");

        while (menu.isRunning()) {
            menu.start();
        }

        scanner.close();
    }
}

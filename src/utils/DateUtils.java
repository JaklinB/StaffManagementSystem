package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return false;
        }
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate parseDate(String dateStr) {
        if (!isValidDate(dateStr)) {
            System.out.println("Invalid date format: " + dateStr);
            System.out.println("Date format should be " + Constants.DATE_FORMAT);
            return null;
        } else {
            return LocalDate.parse(dateStr, formatter);
        }
    }

    public static String formatDate(LocalDate date) {
        if (date == null|| !isValidDate(date.toString())) {
            System.out.println("Invalid date format: " + date);
            System.out.println("Date format should be " + Constants.DATE_FORMAT);
            return null;
        } else {
            return date.format(formatter);
        }
    }

}

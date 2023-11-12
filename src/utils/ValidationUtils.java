package utils;

public class ValidationUtils {

    /**
     * Checks if a string is not null and not empty after trimming.
     *
     * @param str The string to check.
     * @return true if the string is neither null nor empty, false otherwise.
     */
    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Checks if an integer number is positive.
     *
     * @param number The number to check.
     * @return true if the number is positive, false otherwise.
     */
    public static boolean isPositiveInt(int number) {
        return number > 0;
    }

    /**
     * Checks if a double number is positive.
     *
     * @param number The number to check.
     * @return true if the number is positive, false otherwise.
     */
    public static boolean isPositiveDouble(double number) {
        return number > 0;
    }

    /**
     * Validates if a string represents a valid salary.
     * A valid salary is a positive double.
     *
     * @param salaryStr The string representation of the salary.
     * @return true if the string is a valid salary, false otherwise.
     */
    public static boolean isValidSalary(String salaryStr) {
        if (!isStringDouble(salaryStr)) {
            return false;
        }
        double salary = Double.parseDouble(salaryStr);
        return isPositiveDouble(salary);
    }

    /**
     * Validates if a string represents a valid ID.
     * A valid ID is a positive integer.
     *
     * @param idStr The string representation of the ID.
     * @return true if the string is a valid ID, false otherwise.
     */
    public static boolean isValidId(String idStr) {
        if (!isStringInteger(idStr)) {
            return false;
        }
        int id = Integer.parseInt(idStr);
        return isPositiveInt(id);
    }

    /**
     * Checks if a string can be parsed into an integer.
     *
     * @param str The string to check.
     * @return true if the string can be parsed as an integer, false otherwise.
     */
    public static boolean isStringInteger(String str) {
        if (!isNotNullOrEmpty(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string can be parsed into a double.
     *
     * @param str The string to check.
     * @return true if the string can be parsed as a double, false otherwise.
     */
    public static boolean isStringDouble(String str) {
        if (!isNotNullOrEmpty(str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string is a valid enumeration value of a specific enum class.
     *
     * @param enumClass The class of the enum to check against.
     * @param enumValue The string representation of the enum value.
     * @param <E>       The type of the enum.
     * @return true if the string is a valid value of the specified enum, false otherwise.
     */
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumValue) {
        try {
            Enum.valueOf(enumClass, enumValue);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

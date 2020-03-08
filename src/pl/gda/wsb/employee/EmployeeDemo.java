package pl.gda.wsb.employee;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// PR1.1
public class EmployeeDemo {

    public static String companyName = "Logintegra Sp. z o.o.";
    static String fileName = System.getProperty("user.dir") + "\\utils\\db.txt";

    static ArrayList<String> employees = new ArrayList<>();
    static ArrayList<String> loggedEmployees = new ArrayList<>();
    private static DataBase dataBase;
    private static EmployeeRepository  employeeRepository;

    public static void main(String args[]) {

        dataBase = new DataBase();
        employeeRepository = new EmployeeRepository();

        Scanner fileScanner = dataBase.getFileScanner();
        if (fileScanner == null) return;

        Pattern pattern = Pattern.compile("(true|false) - (.+)");
        while (fileScanner.hasNextLine()) {
            String employee = fileScanner.nextLine();
            Matcher matcher = pattern.matcher(employee);
            if (matcher.matches()) {
                employeeRepository.getEmployees().add(employee);
                if (Boolean.parseBoolean(matcher.group(1))) {
                    employeeRepository.getEmployees(true).add(matcher.group(2));
                }
            }
        }
        fileScanner.close();

        EemployeePrinter.printWelcomeText();

        EemployeePrinter.printEmployees();

        EemployeePrinter.printLoggedEmployees();

        employeeRepository.readEmployeeNameAndChangeStatus();


    }

}

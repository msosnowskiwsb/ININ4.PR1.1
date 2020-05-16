package pl.gda.wsb.employee;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// PR1.1
public class EmployeeDemo {

    public static String companyName = "Logintegra Sp. z o.o.";
    static String fileName = System.getProperty("user.dir") + "\\utils\\db.txt";

    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Employee> loggedEmployees = new ArrayList<>();
    private static DataBase dataBase;
    private static EmployeeRepository  employeeRepository;

    public static void main(String args[]) {

        dataBase = new DataBase();
        employeeRepository = new EmployeeRepository();

        Scanner fileScanner = dataBase.getFileScanner();
        if (fileScanner == null) return;

        Pattern pattern = Pattern.compile("(true|false) - (.+) - (.+)");
        while (fileScanner.hasNextLine()) {
            String lineFromFile = fileScanner.nextLine();
            Matcher matcher = pattern.matcher(lineFromFile);
            if (matcher.matches()) {

                boolean status = Boolean.parseBoolean(matcher.group(1));
                String employeeName = matcher.group(2);
                String position = matcher.group(3);

                switch (position){
                    case "dyrektor": {
                        Employee employee = new Director(status,employeeName);
                        employeeRepository.getEmployees().add(employee);
                        if (status){
                            employeeRepository.getEmployees(true).add(employee);
                        }
                        break;
                    }
                    case "sprzedawca": {
                        Employee employee = new Seller(status,employeeName);
                        employeeRepository.getEmployees().add(employee);
                        if (status){
                            employeeRepository.getEmployees(true).add(employee);
                        }
                        break;
                    }
                    case "kierowca": {
                        Employee employee = new Driver(status,employeeName);
                        employeeRepository.getEmployees().add(employee);
                        if (status){
                            employeeRepository.getEmployees(true).add(employee);
                        }
                        break;
                    }
                }

/*                Employee employee = new Employee(
                        Boolean.parseBoolean(matcher.group(1)),
                        matcher.group(2),
                        matcher.group(3)
                );

                employeeRepository.getEmployees().add(employee);
                if (Boolean.parseBoolean(matcher.group(1))) {
                    employeeRepository.getEmployees(true).add(employee);
                }*/
            }
        }
        fileScanner.close();

        EemployeePrinter.printWelcomeText();

        EemployeePrinter.printEmployees();

        EemployeePrinter.printLoggedEmployees();

        employeeRepository.readEmployeeNameAndChangeStatus(EmployeeRepository.getEmployees());

    }

}

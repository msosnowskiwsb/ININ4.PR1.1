package pl.gda.wsb.employee;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeRepository {

    DataBase dataBase = new DataBase();

    void readEmployeeNameAndChangeStatus(ArrayList<Employee> employeeList) {
        System.out.println("\nPodaj imię i nazwisko (exit = koniec):");
        Scanner inScanner = new Scanner(System.in);
        while (inScanner.hasNextLine()) {
            try {
                Employee foundEmployee = searchEmployee(employeeList, inScanner.nextLine());
                foundEmployee.setLogged(!foundEmployee.isLogged());
                System.out.println("Zmieniono status dla pracownika: " + foundEmployee.getName() + " na " + foundEmployee.isLogged() + ".\n\nPodaj imię i nazwisko (exit = koniec):");
            } catch (WrongEmployee exception) {
            }
        }
    }

    private Employee searchEmployee(ArrayList<Employee> employeeList, String employeeNameFromUser) throws WrongEmployee {
        if (employeeNameFromUser.equals("exit")) {
            dataBase.saveToFile(employeeList);
            System.exit(0);
        }

        Pattern patternSearch = Pattern.compile("^(true|false) - " + employeeNameFromUser + " - (.+)$");

        for (Employee employee : employeeList) {
            Matcher matcher = patternSearch.matcher(employee.toString());
            if (matcher.matches()) {
                return employee;
            }
        }
        throw new WrongEmployee();
    }

    static ArrayList<Employee> getEmployees(Boolean onlyLogged) {
        return onlyLogged ? EmployeeDemo.loggedEmployees : EmployeeDemo.employees;
    }

    static ArrayList<Employee> getEmployees() {
        return EmployeeDemo.employees;
    }
}

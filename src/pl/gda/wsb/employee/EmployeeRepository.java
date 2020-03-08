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
            String employeeNameFromUser = inScanner.nextLine();
            if (employeeNameFromUser.equals("exit")) {
                dataBase.saveToFile(getEmployees());
                break;
            }

            Pattern patternSearch = Pattern.compile("^(true|false) - " + employeeNameFromUser + " - (.+)$");
            int i = 0;
            Boolean searched = false;

            for (Employee employee : getEmployees()) {
                Matcher matcher = patternSearch.matcher(employee.toString());
                if (matcher.matches()) {
                    searched = true;
                    boolean isLogged = Boolean.parseBoolean(matcher.group(1));
                    //getEmployees().remove(i);
                    //getEmployees().add(i, employee.replace(matcher.group(1), isLogged ? "false" : "true"));
                    employeeList.get(i).setLogged(!isLogged);
                    break;
                }
                i++;
            }

            if (searched) {
                System.out.println("Zmieniono status dla pracownika: " + employeeNameFromUser);
            } else {
                System.out.println("Błędnie podane imię i nazwisko!");
            }
        }
    }

    static ArrayList<Employee> getEmployees(Boolean onlyLogged){
        return onlyLogged ? EmployeeDemo.loggedEmployees : EmployeeDemo.employees;
    }

    static ArrayList<Employee> getEmployees(){
        return EmployeeDemo.employees;
    }
}

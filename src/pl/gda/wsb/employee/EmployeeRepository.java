package pl.gda.wsb.employee;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeRepository {

    DataBase dataBase = new DataBase();

     void readEmployeeNameAndChangeStatus() {
        System.out.println("\nPodaj imię i nazwisko (exit = koniec):");
        Scanner inScanner = new Scanner(System.in);
        while (inScanner.hasNextLine()) {
            String text = inScanner.nextLine();
            if (text.equals("exit")) {
                dataBase.saveToFile(getEmployees());
                break;
            }

            Pattern patternSearch = Pattern.compile("^(true|false) - " + text + " - (.+)$");
            int i = 0;
            Boolean searched = false;

            for (String employee : getEmployees()) {
                Matcher matcher = patternSearch.matcher(employee);
                if (matcher.matches()) {
                    searched = true;
                    boolean isLogged = Boolean.parseBoolean(matcher.group(1));
                    getEmployees().remove(i);
                    getEmployees().add(i, employee.replace(matcher.group(1), isLogged ? "false" : "true"));
                    break;
                }
                i++;
            }

            if (searched) {
                System.out.println("Zmieniono status dla pracownika: " + text);
            } else {
                System.out.println("Błędnie podane imię i nazwisko!");
            }
        }
    }

    static ArrayList<String> getEmployees(Boolean onlyLogged){
        return onlyLogged ? EmployeeDemo.loggedEmployees : EmployeeDemo.employees;
    }

    static ArrayList<String> getEmployees(){
        return EmployeeDemo.employees;
    }
}

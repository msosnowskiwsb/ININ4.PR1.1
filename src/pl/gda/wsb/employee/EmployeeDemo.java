package pl.gda.wsb.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        printWelcomeText();

        printEmployees();

        printLoggedEmployees();

        employeeRepository.readEmployeeNameAndChangeStatus();


    }

    private static void printEmployees() {
        if (EmployeeRepository.getEmployees().size() == 0) {
            System.out.println("Brak pracowników");
        } else {
            System.out.println("Liczba pracowników: " + EmployeeRepository.getEmployees().size());
        }

        if (EmployeeRepository.getEmployees().size() > 0) {
            System.out.println("\nLista pracowników:");
            for (int i = 0; i < EmployeeRepository.getEmployees().size(); i++) {
                if (i == 5) {
                    System.out.println("...");
                    break;
                }
                System.out.println(EmployeeRepository.getEmployees().get(i));
            }
        }
    }

    private static void printLoggedEmployees() {
        if (EmployeeRepository.getEmployees(true).size() > 0) {
            System.out.println("\nZalogowani użytkownicy: (" + EmployeeRepository.getEmployees(true).size() + "):");
            for (int i = 0; i < EmployeeRepository.getEmployees(true).size(); i++) {
                if (i == 5) {
                    System.out.println("...");
                    break;
                }
                System.out.println(EmployeeRepository.getEmployees(true).get(i));
            }
        }
    }

    private static void printWelcomeText() {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy HH:mm");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ").append(DataBase.getOperatorName()).append("!").append("\n")
                .append("Aktualna data: ").append(ft.format(new Date()))
                .append("\nNazwa operatora ").append(DataBase.getOperatorName())
                .append("\nNazwa firmy: ").append(companyName);
        System.out.println(stringBuilder);
    }

}

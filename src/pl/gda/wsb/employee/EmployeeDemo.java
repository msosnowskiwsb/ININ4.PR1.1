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

    public static void main(String args[]) {

        Scanner fileScanner = dataBase.getFileScanner();
        if (fileScanner == null) return;

        Pattern pattern = Pattern.compile("(true|false) - (.+)");
        while (fileScanner.hasNextLine()) {
            String employee = fileScanner.nextLine();
            Matcher matcher = pattern.matcher(employee);
            if (matcher.matches()) {
                getEmployees().add(employee);
                if (Boolean.parseBoolean(matcher.group(1))) {
                    getEmployees(true).add(matcher.group(2));
                }
            }
        }
        fileScanner.close();

        printWelcomeText();

        printEmployees();

        printLoggedEmployees();

        readEmployeeNameAndChangeStatus();


    }

    private static void readEmployeeNameAndChangeStatus() {
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

    private static void printEmployees() {
        if (getEmployees().size() == 0) {
            System.out.println("Brak pracowników");
        } else {
            System.out.println("Liczba pracowników: " + getEmployees().size());
        }

        if (getEmployees().size() > 0) {
            System.out.println("\nLista pracowników:");
            for (int i = 0; i < getEmployees().size(); i++) {
                if (i == 5) {
                    System.out.println("...");
                    break;
                }
                System.out.println(getEmployees().get(i));
            }
        }
    }

    private static void printLoggedEmployees() {
        if (getEmployees(true).size() > 0) {
            System.out.println("\nZalogowani użytkownicy: (" + getEmployees(true).size() + "):");
            for (int i = 0; i < getEmployees(true).size(); i++) {
                if (i == 5) {
                    System.out.println("...");
                    break;
                }
                System.out.println(getEmployees(true).get(i));
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

    private static ArrayList<String> getEmployees(Boolean onlyLogged){
        return onlyLogged ? loggedEmployees : employees;
    }

    private static ArrayList<String> getEmployees(){
        return employees;
    }

}

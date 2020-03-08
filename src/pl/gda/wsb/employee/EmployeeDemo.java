package pl.gda.wsb.employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void main(String args[]) {
        String operatorName = args[0];
        Integer empoyeesCounter = 14;
        ArrayList<String> employees = new ArrayList<>();
        ArrayList<String> loggedEmployess = new ArrayList<>();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy HH:mm"); // Dodano po zajęciach - zdefiniowanie formatu daty

        File file = new File(fileName);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Błąd pobrania pliku.");
            return;
        }

        Pattern pattern = Pattern.compile("(true|false) - (.+)");
        while (fileScanner.hasNextLine()) {
            String employee = fileScanner.nextLine();
            Matcher matcher = pattern.matcher(employee);
            if (matcher.matches()) {
                employees.add(employee);
                if (Boolean.parseBoolean(matcher.group(1))) {
                    loggedEmployess.add(matcher.group(2));
                }
            }
        }
        fileScanner.close();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ").append(args[1]).append("!").append("\n")
                .append("Aktualna data: ").append(ft.format(new Date())) // Zmiana linii po zajęciach - użycie innego formatu
                .append("Nazwa operatora ").append(operatorName)
                .append("Nazwa firmy: ").append(companyName);
        System.out.println(stringBuilder);

        if (empoyeesCounter == null) {
            System.out.println("Błąd pobieraania pracowników");
        } else if (empoyeesCounter == 0) {
            System.out.println("Brak pracowników");
        } else {
            System.out.println("Liczba pracowników: " + empoyeesCounter);
        }

        if (loggedEmployess.size() > 0) {
            System.out.println("Zalogowani użytkownicy:");
            for (int i = 0; i < loggedEmployess.size(); i++) {
                if (i == 5) {
                    System.out.println("...");
                    break;
                }
                System.out.println(loggedEmployess.get(i));
            }
        }

        System.out.println("\nPodaj imię i nazwisko (exit = koniec):");
        Scanner inScanner = new Scanner(System.in);
        while (inScanner.hasNextLine()) {
            String text = inScanner.nextLine();
            if (text.equals("exit")) {
                // Linie 80-89 dodane po zajęciach - zapis do pliku
                FileWriter fw = null;
                try {
                    fw = new FileWriter(fileName, false);
                    for (String employee : employees) {
                        fw.write(employee + "\n");
                    }
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Błąd zapisu pliku!");
                }
                break;
            }

            Pattern patternSearch = Pattern.compile("^(true|false) - " + text + " - (.+)$");
            int i = 0;
            Boolean searched = false;  // Dodane po zajęciach

            for (String employee : employees) {
                Matcher matcher = patternSearch.matcher(employee);
                if (matcher.matches()) {
                    searched = true;  // Dodane po zajęciach
                    boolean isLogged = Boolean.parseBoolean(matcher.group(1));
                    employees.remove(i);
                    employees.add(i, employee.replace(matcher.group(1), isLogged ? "false" : "true"));
                    break; // Dodane po zajęciach - brak tego break powodował błąd
                }
                i++;
            }

            // Poniższe linie (110-114) dodane po zajęciach - wyświetlanie info czy status został zmieniony, czy błędne dane
            if (searched) {
                System.out.println("Zmieniono status dla pracownika: " + text);
            } else {
                System.out.println("Błędnie podane imię i nazwisko!");
            }
        }


    }

}

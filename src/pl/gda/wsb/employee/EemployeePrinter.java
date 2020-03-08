package pl.gda.wsb.employee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EemployeePrinter {
    static void printEmployees() {
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

    static void printLoggedEmployees() {
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

    static void printWelcomeText() {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy HH:mm");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello ").append(DataBase.getOperatorName()).append("!").append("\n")
                .append("Aktualna data: ").append(ft.format(new Date()))
                .append("\nNazwa operatora ").append(DataBase.getOperatorName())
                .append("\nNazwa firmy: ").append(EmployeeDemo.companyName);
        System.out.println(stringBuilder);
    }
}

package pl.gda.wsb.employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {

    static void saveToFile(ArrayList<Employee> employeesListToSave) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(EmployeeDemo.fileName, false);
            for (Employee employee : employeesListToSave) {
                fw.write(employee + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Błąd zapisu pliku!");
        }
    }

    static Scanner getFileScanner() {
        File file = new File(EmployeeDemo.fileName);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Błąd pobrania pliku.");
            e.printStackTrace();
            return null;
        }
        return fileScanner;
    }

    static String getOperatorName(){
        return "Mateusz";
    }
}

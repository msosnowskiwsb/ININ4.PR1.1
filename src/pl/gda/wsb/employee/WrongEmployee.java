package pl.gda.wsb.employee;

public class WrongEmployee extends Exception{
    public WrongEmployee(){
        System.out.println("Nie znaleziono pracownika w bazie!\n\nProszę ponownie podać imię i nazwisko pracownika (exit = koniec):");
        // WrongEmployee.this.printStackTrace();
    }
}

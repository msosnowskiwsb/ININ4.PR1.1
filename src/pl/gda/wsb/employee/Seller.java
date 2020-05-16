package pl.gda.wsb.employee;

public class Seller extends Employee{
    public Seller(boolean logged, String name) {
        super(logged, name);
    }

    @Override
    public String getPosition() {
        return "sprzedawca";
    }
}

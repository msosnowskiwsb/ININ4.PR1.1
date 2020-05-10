package pl.gda.wsb.employee;

public class Driver extends Employee{

    public Driver(boolean logged, String name) {
        super(logged, name);
    }

    @Override
    public String getPosition() {
        return "kierowca";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

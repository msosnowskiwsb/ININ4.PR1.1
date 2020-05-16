package pl.gda.wsb.employee;

public class Director extends Employee{

    public Director(boolean logged, String name) {
        super(logged, name);
    }

    @Override
    public String getPosition() {
        return "dyrektor";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

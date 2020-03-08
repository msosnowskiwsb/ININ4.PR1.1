package pl.gda.wsb.employee;

public class Employee {
    boolean logged;
    String name;
    String position;

    public Employee(boolean logged, String name, String position) {
        this.logged = logged;
        this.name = name;
        this.position = position;
    }

    public boolean isLogged() {
        return logged;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return logged + " - " + name + " - " + position;
    }
}

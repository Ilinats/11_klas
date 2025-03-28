package zad1;

public class Archer extends Character {
    public Archer(String name) {
        super(name, 120, 40, 15, "Arrow");
    }
    public void display() {
        System.out.println("Archer: " + name);
    }
}

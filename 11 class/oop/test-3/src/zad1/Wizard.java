package zad1;

public class Wizard extends Character {
    public Wizard(String name) {
        super(name, 100, 50, 10, "Fireball");
    }
    public void display() {
        System.out.println("Wizard: " + name);
    }
}

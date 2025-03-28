package zad1;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 200, 30, 20, "Fire");
    }
    public void display() {
        System.out.println("Warrior: " + name);
    }
}

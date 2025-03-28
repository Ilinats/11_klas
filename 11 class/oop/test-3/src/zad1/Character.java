package zad1;

abstract class Character {
    protected String name;
    protected int health, attack, defense;
    protected String specialAbility;

    public Character(String name, int health, int attack, int defense, String specialAbility) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.specialAbility = specialAbility;
    }

    public abstract void display();
}

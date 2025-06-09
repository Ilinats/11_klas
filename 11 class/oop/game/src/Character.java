public abstract class Character implements Interactable {
    private final String name;
    private int health;

    protected Character(String name, int health) {
        this.name = name;
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, 100));
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Each subclass will define how it interacts
    public abstract String interact(Interactable target);
}
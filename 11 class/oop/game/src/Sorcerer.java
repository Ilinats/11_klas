public class Sorcerer extends Character {
    private int mana;

    public Sorcerer(String name, int health, int mana) {
        super(name, health);
        setMana(mana);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(mana, 50));
    }

    @Override
    public String interact(Interactable target) {
        if (target instanceof Character opponent) {
            opponent.setHealth(opponent.getHealth() - 25);
            return getName() + " casts a lightning bolt for 25 damage!";
        }
        return getName() + " can't interact with " + target.getName();
    }
}
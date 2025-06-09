public class Knight extends Character {
    private int armor; // private attribute

    public Knight(String name, int health, int armor) {
        super(name, health);
        setArmor(armor);
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = Math.max(0, armor);
    }

    @Override
    public String interact(Interactable target) {
        if (target instanceof Character) {
            Character opponent = (Character) target;
            opponent.setHealth(opponent.getHealth() - 20);
            return getName() + " swings a sword for 20 damage!";
        }
        return getName() + " can't interact with " + target.getName();
    }
}
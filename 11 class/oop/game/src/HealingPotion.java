public class HealingPotion implements Interactable {
    private String name;
    private final int healAmount = 30;

    public HealingPotion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String interact(Interactable target) {
        if (target instanceof Character character) {
            character.setHealth(character.getHealth() + healAmount);
            return getName() + " restores " + healAmount + " health!";
        }
        return getName() + " can't heal " + target.getName();
    }
}
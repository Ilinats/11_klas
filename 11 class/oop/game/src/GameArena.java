public class GameArena {
    public static void main(String[] args) {
        Knight leon = new Knight("Leon", 100, 50);
        Sorcerer mira = new Sorcerer("Mira", 80, 40);
        HealingPotion elixir = new HealingPotion("Elixir");

        Interactable[] entities = { leon, mira, elixir };

        for (Interactable entity : entities) {
            String result = "";

            if (entity instanceof Knight) {
                result = entity.interact(mira);
            } else if (entity instanceof Sorcerer) {
                result = entity.interact(leon);
            } else if (entity instanceof HealingPotion) {
                Character toHeal = (leon.getHealth() < mira.getHealth()) ? leon : mira;
                result = entity.interact(toHeal);
            }

            System.out.println(result);
        }

        System.out.println("\nFinal Status:");
        System.out.println(leon.getName() + " health: " + leon.getHealth() + " | Alive: " + leon.isAlive());
        System.out.println(mira.getName() + " health: " + mira.getHealth() + " | Alive: " + mira.isAlive());
    }
}
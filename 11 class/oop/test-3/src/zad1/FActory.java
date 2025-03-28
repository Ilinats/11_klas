package zad1;

class FActory {
    public static Character createCharacter(String type, String name) {
        return switch (type.toLowerCase()) {
            case "warrior" -> new Warrior(name);
            case "wizard" -> new Wizard(name);
            case "archer" -> new Archer(name);
            default -> throw new IllegalArgumentException("Unknown character type");
        };
    }
}
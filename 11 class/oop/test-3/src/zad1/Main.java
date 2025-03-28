package zad1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type, name;

        while (true) {
            System.out.print("Enter character type (Warrior, Wizard, Archer) or 'quit' to exit: ");
            type = scanner.nextLine();
            if (type.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.print("Enter character name: ");
            name = scanner.nextLine();

            try {
                Character character = FActory.createCharacter(type, name);
                character.display();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
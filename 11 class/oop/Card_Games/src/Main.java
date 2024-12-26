import org.elsys.cardgame.CardException;
import org.elsys.cardgame.api.*;
import org.elsys.cardgame.impl.CardImpl;
import org.elsys.cardgame.operations.GameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Game game = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Card> initialCards = new ArrayList<>();
        String[] cardStrings = null;

        System.out.println("Deck: (Please enter a list of cards, separated by spaces)");
        if (scanner.hasNextLine()) {
            cardStrings = scanner.nextLine().split(" ");
        }

        System.out.println("Game Type: (Please enter one of the following: War, Belote, Santase)");
        String gameType = scanner.nextLine().trim();

        for (String cardString : cardStrings) {
            initialCards.add(parseCard(cardString, gameType));
        }

        createGame(initialCards, gameType);

        while (scanner.hasNextLine()) {
            String operation = scanner.nextLine().trim();
            if (operation.equals("quit")) {
                break;
            }

            if (game == null) {
                System.out.println("ERROR: No deck");
            } else {
                try {
                    game.process(operation);
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR: Unknown operation");
                } catch (CardException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void createGame(List<Card> initialCards, String gameType) {
        switch (gameType) {
            case "War":
                if (initialCards.size() < 52) {
                    System.out.println("ERROR: Not enough cards for War");
                } else {
                    game = GameFactory.createGame(initialCards.subList(0, 52), 26);
                }
                break;
            case "Belote":
                if (initialCards.size() < 32) {
                    System.out.println("ERROR: Not enough cards for Belote");
                } else {
                    for (Card card : initialCards.subList(0, 32)) {
                        if (!(card.getRank() instanceof BelotRank)) {
                            System.out.println("ERROR: Invalid card for Belote: " + card);
                            return;
                        }
                    }
                    game = GameFactory.createGame(initialCards.subList(0, 32), 8);
                }
                break;
            case "Santase":
                if (initialCards.size() < 24) {
                    System.out.println("ERROR: Not enough cards for Santase");
                } else {
                    for (Card card : initialCards.subList(0, 24)) {
                        if (!(card.getRank() instanceof SantaseRank)) {
                            System.out.println("ERROR: Invalid card for Santase: " + card);
                            return;
                        }
                    }
                    game = GameFactory.createGame(initialCards.subList(0, 24), 6);
                }
                break;
            default:
                System.out.println("ERROR: Unknown game type");
                return;
        }
    }

    private static Card parseCard(String cardString, String gameType) {
        if (cardString.length() < 2 || cardString.length() > 3) {
            throw new IllegalArgumentException("Invalid card format: " + cardString);
        }

        Suit suit = parseSuit(cardString.charAt(0));
        Rank rank = parseRank(cardString.substring(1), gameType);
        return new CardImpl(rank, suit);
    }

    private static Suit parseSuit(char suitChar) {
        switch (suitChar) {
            case 'S': return Suit.SPADES;
            case 'H': return Suit.HEARTS;
            case 'D': return Suit.DIAMONDS;
            case 'C': return Suit.CLUBS;
            default: throw new IllegalArgumentException("Invalid suit: " + suitChar);
        }
    }

    private static Rank parseRank(String rankString, String gameType) {
        if ("Santase".equals(gameType)) {
            switch (rankString) {
                case "9": return SantaseRank.NINE;
                case "J": return SantaseRank.JACK;
                case "Q": return SantaseRank.QUEEN;
                case "K": return SantaseRank.KING;
                case "10": return SantaseRank.TEN;
                case "A": return SantaseRank.ACE;
                default: throw new IllegalArgumentException("Invalid rank: " + rankString);
            }
        } else if ("Belote".equals(gameType)) {
            switch (rankString) {
                case "9": return BelotRank.NINE;
                case "10": return BelotRank.TEN;
                case "J": return BelotRank.JACK;
                case "Q": return BelotRank.QUEEN;
                case "K": return BelotRank.KING;
                case "A": return BelotRank.ACE;
                default: throw new IllegalArgumentException("Invalid rank: " + rankString);
            }
        } else {
            switch (rankString) {
                case "2": return WarRank.TWO;
                case "3": return WarRank.THREE;
                case "4": return WarRank.FOUR;
                case "5": return WarRank.FIVE;
                case "6": return WarRank.SIX;
                case "7": return WarRank.SEVEN;
                case "8": return WarRank.EIGHT;
                case "9": return WarRank.NINE;
                case "10": return WarRank.TEN;
                case "J": return WarRank.JACK;
                case "Q": return WarRank.QUEEN;
                case "K": return WarRank.KING;
                case "A": return WarRank.ACE;
                default: throw new IllegalArgumentException("Invalid rank: " + rankString);
            }
        }
    }
}
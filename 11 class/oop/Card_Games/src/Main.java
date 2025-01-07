import org.elsys.cardgame.CardException;
import org.elsys.cardgame.api.*;
import org.elsys.cardgame.impl.CardImpl;
import org.elsys.cardgame.operations.GameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Game game = null;
    private static List<Card> initialCards = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cardStrings = null;

        System.out.println("Deck: (Please enter a list of cards, separated by spaces)");
        if (scanner.hasNextLine()) {
            cardStrings = scanner.nextLine().split(" ");
        }

        while (true) {
            System.out.println("Game Type: (Please enter one of the following: War, Belote, Santase)");
            String gameType = scanner.nextLine().trim();

            if(gameType.equals("quit")) {
                break;
            }

            assert cardStrings != null;
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
                    System.out.println("ERROR: No deck, type quit to exit");
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
    }

    private static void createGame(List<Card> initialCards, String gameType) {
        try {
            switch (gameType) {
                case "War":
                    game = GameFactory.createWarGame(initialCards);
                    break;
                case "Belote":
                    game = GameFactory.createBeloteGame(initialCards);
                    break;
                case "Santase":
                    game = GameFactory.createSantaseGame(initialCards);
                    break;
                default:
                    System.out.println("ERROR: Unknown game type");
                    return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Card parseCard(String cardString, String gameType) {
        if (cardString.length() < 2 || cardString.length() > 3) {
            throw new IllegalArgumentException("Invalid card format: " + cardString);
        }

        Suit suit = parseSuit(cardString.charAt(0));
        Rank rank;

        if(gameType.equals("War")) {
            rank = parseRankWar(cardString.substring(1));
        } else if(gameType.equals("Belote")) {
            rank = parseRankBelot(cardString.substring(1));
        } else if(gameType.equals("Santase")) {
            rank = parseRankSantase(cardString.substring(1));
        } else {
            throw new IllegalArgumentException("Invalid game type: " + gameType);
        }

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

    private static Rank parseRankWar(String rankString) {
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

    private static Rank parseRankBelot(String rankSting) {
        switch (rankSting) {
            case "7": return BelotRank.SEVEN;
            case "8": return BelotRank.EIGHT;
            case "9": return BelotRank.NINE;
            case "10": return BelotRank.TEN;
            case "J": return BelotRank.JACK;
            case "Q": return BelotRank.QUEEN;
            case "K": return BelotRank.KING;
            case "A": return BelotRank.ACE;
            default: throw new IllegalArgumentException("Invalid rank: " + rankSting);
        }
    }

    private static Rank parseRankSantase(String rankString) {
        switch (rankString) {
            case "9": return SantaseRank.NINE;
            case "J": return SantaseRank.JACK;
            case "Q": return SantaseRank.QUEEN;
            case "K": return SantaseRank.KING;
            case "10": return SantaseRank.TEN;
            case "A": return SantaseRank.ACE;
            default: throw new IllegalArgumentException("Invalid rank: " + rankString);
        }
    }
}
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
            System.out.println("Game Type: (Please enter one of the following: War, Belote, Santase or quit)");
            String gameType = scanner.nextLine().trim();

            if(gameType.equals("quit")) {
                break;
            }

            assert cardStrings != null;
            for (String cardString : cardStrings) {
                try {
                    initialCards.add(parseCard(cardString, gameType));
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR: Invalid card format: " + cardString);

                    initialCards.clear();
                    break;
                }
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
        Rank rank = null;

        if(gameType.equals("War")) {
            try {
                rank = parseRankWar(cardString.substring(1));
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Invalid rank: " + cardString.substring(1));
            }
        } else if(gameType.equals("Belote")) {
            try {
                rank = parseRankBelot(cardString.substring(1));
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Invalid rank: " + cardString.substring(1));
            }
        } else if(gameType.equals("Santase")) {
            try {
                rank = parseRankSantase(cardString.substring(1));
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Invalid rank: " + cardString.substring(1));
            }
        } else {
            throw new IllegalArgumentException("Invalid game type: " + gameType);
        }

        if(rank == null) {
            throw new IllegalArgumentException("Invalid rank: " + cardString.substring(1));
        }

        return new CardImpl(rank, suit);
    }

    private static Suit parseSuit(char suitChar) {
        return switch (suitChar) {
            case 'S' -> Suit.SPADES;
            case 'H' -> Suit.HEARTS;
            case 'D' -> Suit.DIAMONDS;
            case 'C' -> Suit.CLUBS;
            default -> throw new IllegalArgumentException("Invalid suit: " + suitChar);
        };
    }

    private static Rank parseRankWar(String rankString) {
        return switch (rankString) {
            case "2" -> WarRank.TWO;
            case "3" -> WarRank.THREE;
            case "4" -> WarRank.FOUR;
            case "5" -> WarRank.FIVE;
            case "6" -> WarRank.SIX;
            case "7" -> WarRank.SEVEN;
            case "8" -> WarRank.EIGHT;
            case "9" -> WarRank.NINE;
            case "10" -> WarRank.TEN;
            case "J" -> WarRank.JACK;
            case "Q" -> WarRank.QUEEN;
            case "K" -> WarRank.KING;
            case "A" -> WarRank.ACE;
            default -> throw new IllegalArgumentException("Invalid rank: " + rankString);
        };
    }

    private static Rank parseRankBelot(String rankSting) {
        return switch (rankSting) {
            case "7" -> BelotRank.SEVEN;
            case "8" -> BelotRank.EIGHT;
            case "9" -> BelotRank.NINE;
            case "10" -> BelotRank.TEN;
            case "J" -> BelotRank.JACK;
            case "Q" -> BelotRank.QUEEN;
            case "K" -> BelotRank.KING;
            case "A" -> BelotRank.ACE;
            default -> throw new IllegalArgumentException("Invalid rank: " + rankSting);
        };
    }

    private static Rank parseRankSantase(String rankString) {
        return switch (rankString) {
            case "9" -> SantaseRank.NINE;
            case "J" -> SantaseRank.JACK;
            case "Q" -> SantaseRank.QUEEN;
            case "K" -> SantaseRank.KING;
            case "10" -> SantaseRank.TEN;
            case "A" -> SantaseRank.ACE;
            default -> throw new IllegalArgumentException("Invalid rank: " + rankString);
        };
    }
}
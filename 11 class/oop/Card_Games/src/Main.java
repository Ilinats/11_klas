//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.elsys.cardgame.api.Deck;
import org.elsys.cardgame.impl.DeckFactory;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deck warDeck = DeckFactory.createWarDeck();
        Deck beloteDeck = DeckFactory.createBeloteDeck();
        Deck santaseDeck = DeckFactory.createSantaseDeck();

        System.out.println(warDeck.getCards().size());
        System.out.println(beloteDeck.getCards().size());
        LinkedList<?> santaseCards = new LinkedList<>(santaseDeck.getCards());
        System.out.println(santaseCards.removeFirst());
        System.out.println(santaseCards.removeFirst());
    }
}
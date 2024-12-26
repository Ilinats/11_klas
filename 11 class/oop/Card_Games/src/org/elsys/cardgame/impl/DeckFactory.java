package org.elsys.cardgame.impl;

import org.elsys.cardgame.api.*;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {
    public static Deck createWarDeck() {
        return createDeck(WarRank.values(), Suit.values(), 26);
    }

    public static Deck createBeloteDeck() {
        return createDeck(BelotRank.values(), Suit.values(), 8);
    }

    public static Deck createSantaseDeck() {
        return createDeck(SantaseRank.values(), Suit.values(), 6);
    }

    private static Deck createDeck(Rank[] ranks, Suit[] suits, int handSize) {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                cards.add(new CardImpl(rank, suit));
            }
        }
        return new DeckImpl(cards, handSize);
    }
}
package org.elsys.cardgame.impl;

import org.elsys.cardgame.api.*;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {
    public static Deck createWarDeck(List<Card> cards) {
        return createDeck(cards, WarRank.values(), Suit.values(), 52, 26);
    }

    public static Deck createBeloteDeck(List<Card> cards) {
        return createDeck(cards, BelotRank.values(), Suit.values(), 32, 8);
    }

    public static Deck createSantaseDeck(List<Card> cards) {
        return createDeck(cards, SantaseRank.values(), Suit.values(), 24, 6);
    }

    private static Deck createDeck(List<Card> cards, Rank[] ranks, Suit[] suits, int requiredSize, int handSize) {
        List<Card> validCards = new ArrayList<>();
        for (Card card : cards) {
            if (isValidCardForGame(card, ranks, suits)) {
                validCards.add(card);
                if (validCards.size() == requiredSize) {
                    return new DeckImpl(validCards, handSize);
                }
            }
        }
        throw new IllegalArgumentException("ERROR: Not enough valid cards for the game");
    }

    private static boolean isValidCardForGame(Card card, Rank[] ranks, Suit[] suits) {
        for (Suit suit : suits) {
            if (card.getSuit() == suit) {
                for (Rank rank : ranks) {
                    if (card.getRank() == rank) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
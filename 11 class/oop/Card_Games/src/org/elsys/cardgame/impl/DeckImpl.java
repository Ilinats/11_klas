package org.elsys.cardgame.impl;

import org.elsys.cardgame.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DeckImpl implements Deck {
    private final List<Card> cards;
    private final int handSize;

    public DeckImpl(List<Card> cards, int handSize) {
        this.cards = new ArrayList<>(cards);
        this.handSize = handSize;
    }

    public DeckImpl(List<Card> cards) {
        this(cards, 0);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public int handSize() {
        return handSize;
    }

    @Override
    public Hand deal() {
        List<Card> handCards = new ArrayList<>(cards.subList(0, handSize));
        cards.subList(0, handSize).clear();
        return new HandImpl(handCards);
    }

    @Override
    public void sort() {
        cards.sort(
                Comparator.comparingInt((Card c) -> c.getSuit().ordinal())
                        .thenComparingInt(c -> c.getRank().ordinal()).reversed()
        );
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public Card drawTopCard() {
        return cards.removeFirst();
    }

    @Override
    public Card topCard() {
        return cards.getFirst();
    }

    @Override
    public Card drawBottomCard() {
        return cards.removeLast();
    }

    @Override
    public Card bottomCard() {
        return cards.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
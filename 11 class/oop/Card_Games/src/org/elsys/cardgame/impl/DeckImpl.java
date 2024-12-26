package org.elsys.cardgame.impl;

import org.elsys.cardgame.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class DeckImpl implements Deck {
    private final List<Card> cards;
    private final int handSize;

    protected DeckImpl(List<Card> cards, int handSize) {
        this.cards = new ArrayList<>(cards);
        this.handSize = handSize;
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
        cards.sort(Comparator.comparingInt((Card c) -> c.getSuit().ordinal()).thenComparingInt(c -> c.getRank().ordinal()));
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public Card drawTopCard() {
        return cards.removeFirst();
    }
}
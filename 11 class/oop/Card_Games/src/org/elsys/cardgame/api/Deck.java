package org.elsys.cardgame.api;

import java.util.List;

public interface Deck {
    List<Card> getCards();
    int size();
    int handSize();
    Hand deal();
    void sort();
    void shuffle();
    Card drawTopCard();
}

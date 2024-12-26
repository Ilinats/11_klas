package org.elsys.cardgame.api;

import java.util.List;

public interface Game {
    Deck getDeck();
    Hand getLastHand();
    List<Operation> getOperations();
    void process(String operationName);
    void setLastHand(Hand hand);
}

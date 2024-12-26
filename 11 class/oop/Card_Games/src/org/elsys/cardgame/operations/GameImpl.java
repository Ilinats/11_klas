package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Deck;
import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Hand;
import org.elsys.cardgame.api.Operation;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {
    private final Deck deck;
    private Hand lastHand;
    private final List<Operation> operations = new ArrayList<>();

    public GameImpl(Deck deck) {
        this.deck = deck;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public Hand getLastHand() {
        return lastHand;
    }

    @Override
    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public void process(String operationName) {
        Operation operation = operations.stream()
                .filter(op -> op.getName().equals(operationName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ERROR: Operation not found."));
        operation.execute();
    }

    @Override
    public void setLastHand(Hand hand) {
        this.lastHand = hand;
    }

    public void addOperation(Operation operation) {
        operations.add(operation);
    }
}
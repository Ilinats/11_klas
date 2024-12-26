package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;
import org.elsys.cardgame.CardException;

public class DealOperation implements Operation {
    private final Game game;

    public DealOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "deal";
    }

    @Override
    public void execute() {
        if (game.getDeck().size() < game.getDeck().handSize()) {
            throw new CardException("ERROR: Not enough cards in deck.");
        }
        var hand = game.getDeck().deal();
        game.setLastHand(hand);
        System.out.println(hand);
    }
}

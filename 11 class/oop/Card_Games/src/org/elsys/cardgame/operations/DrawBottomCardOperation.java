package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;
import org.elsys.cardgame.CardException;

public class DrawBottomCardOperation implements Operation {
    private final Game game;

    public DrawBottomCardOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "draw_bottom_card";
    }

    @Override
    public void execute() {
        if (game.getDeck().size() == 0) {
            throw new CardException("ERROR: Not enough cards in deck.");
        }
        System.out.println(game.getDeck().drawBottomCard());
    }
}
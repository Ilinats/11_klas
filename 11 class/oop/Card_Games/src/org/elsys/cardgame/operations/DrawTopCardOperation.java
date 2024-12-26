package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;
import org.elsys.cardgame.CardException;

public class DrawTopCardOperation implements Operation {
    private final Game game;

    public DrawTopCardOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "draw_top_card";
    }

    @Override
    public void execute() {
        if (game.getDeck().size() == 0) {
            throw new CardException("ERROR: Not enough cards in deck.");
        }
        System.out.println(game.getDeck().drawTopCard());
    }
}
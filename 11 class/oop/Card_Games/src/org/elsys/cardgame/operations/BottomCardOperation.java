package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;

public class BottomCardOperation implements Operation {
    private final Game game;

    public BottomCardOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "bottom_card";
    }

    @Override
    public void execute() {
        System.out.println(game.getDeck().bottomCard());
    }
}
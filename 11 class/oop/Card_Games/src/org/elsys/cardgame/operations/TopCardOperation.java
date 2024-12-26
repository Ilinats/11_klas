package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;

public class TopCardOperation implements Operation {
    private final Game game;

    public TopCardOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "top_card";
    }

    @Override
    public void execute() {
        System.out.println(game.getDeck().topCard());
    }
}
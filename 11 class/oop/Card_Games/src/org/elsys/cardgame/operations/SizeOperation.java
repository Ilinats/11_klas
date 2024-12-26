package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;

public class SizeOperation implements Operation {
    private final Game game;

    public SizeOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "size";
    }

    @Override
    public void execute() {
        System.out.println(game.getDeck().size());
    }
}
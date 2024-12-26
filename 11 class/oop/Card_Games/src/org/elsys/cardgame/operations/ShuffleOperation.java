package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;

import java.util.Collections;

public class ShuffleOperation implements Operation {
    private final Game game;

    public ShuffleOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "shuffle";
    }

    @Override
    public void execute() {
        Collections.shuffle(game.getDeck().getCards());
        System.out.println(game.getDeck());
    }
}
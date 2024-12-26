package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.Game;
import org.elsys.cardgame.api.Operation;

import java.util.Comparator;

public class SortOperation implements Operation {
    private final Game game;

    public SortOperation(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return "sort";
    }

    @Override
    public void execute() {
        game.getDeck().sort();
        System.out.println(game.getDeck());
    }
}
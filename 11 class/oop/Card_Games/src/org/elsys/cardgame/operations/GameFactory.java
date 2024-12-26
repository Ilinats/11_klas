package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.*;
import org.elsys.cardgame.impl.DeckImpl;
import org.elsys.cardgame.operations.*;

import java.util.List;

public class GameFactory {
    public static Game createGame(List<Card> cards, int handSize) {
        Deck deck = new DeckImpl(cards, handSize);
        GameImpl game = new GameImpl(deck);

        game.addOperation(new SizeOperation(game));
        game.addOperation(new DrawTopCardOperation(game));
        game.addOperation(new DrawBottomCardOperation(game));
        game.addOperation(new TopCardOperation(game));
        game.addOperation(new BottomCardOperation(game));
        game.addOperation(new ShuffleOperation(game));
        game.addOperation(new SortOperation(game));
        game.addOperation(new DealOperation(game));

        return game;
    }
}
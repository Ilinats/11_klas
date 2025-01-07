package org.elsys.cardgame.operations;

import org.elsys.cardgame.api.*;
import org.elsys.cardgame.impl.DeckFactory;

import java.util.List;

public class GameFactory {
    public static Game createWarGame(List<Card> cards) {
        Deck deck = DeckFactory.createWarDeck(cards);
        return createGame(deck);
    }

    public static Game createBeloteGame(List<Card> cards) {
        Deck deck = DeckFactory.createBeloteDeck(cards);
        return createGame(deck);
    }

    public static Game createSantaseGame(List<Card> cards) {
        Deck deck = DeckFactory.createSantaseDeck(cards);
        return createGame(deck);
    }

    private static Game createGame(Deck deck) {
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
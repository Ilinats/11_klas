package org.elsys.cardgame.api;

public enum BelotRank implements Rank {
    SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private final String rankValue;

    BelotRank(String rankValue) {
        this.rankValue = rankValue;
    }

    @Override
    public String toString() {
        return rankValue;
    }
}

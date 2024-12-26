package org.elsys.cardgame.api;

public enum SantaseRank implements Rank {
    NINE("9"), JACK("J"), QUEEN("Q"), KING("K"), TEN("10"), ACE("A");

    private final String rankValue;

    SantaseRank(String rankValue) {
        this.rankValue = rankValue;
    }

    @Override
    public String toString() {
        return rankValue;
    }
}
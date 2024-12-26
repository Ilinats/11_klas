package org.elsys.cardgame.api;

public enum WarRank implements Rank{
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private final String rankValue;

    WarRank(String rankValue) {
        this.rankValue = rankValue;
    }

    @Override
    public String toString() {
        return rankValue;
    }
}

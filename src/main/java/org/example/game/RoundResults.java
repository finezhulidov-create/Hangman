package org.example.game;

public class RoundResults {
    private int wins;
    private int loses;
    private int roundCounter;

    @Override
    public String toString() {
        return "Результаты игры: " +
                "Победы :" + wins + "\n" +
                "Поражения: " + loses + "\n" +
                "Количество раундов: " + roundCounter;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof RoundResults that)) return false;

        return wins == that.wins && loses == that.loses && roundCounter == that.roundCounter;
    }

    @Override
    public int hashCode() {
        int result = wins;
        result = 31 * result + loses;
        result = 31 * result + roundCounter;
        return result;
    }
}
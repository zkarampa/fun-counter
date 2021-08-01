package io.zkarampa.funcounter;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int nAnswers;
    private int nQuestions;
    private List<Player> players = new ArrayList<>();

    public Game(int nQuestions, int nAnswers) {
        this.nQuestions = nQuestions;
        this.nAnswers = nAnswers;
    }

    public int getnAnswers() {
        return nAnswers;
    }

    public int getnQuestions() {
        return nQuestions;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public String toString() {
        return "Game{" +
                "nAnswers=" + nAnswers +
                ", nQuestions=" + nQuestions +
                ", players=" + players +
                '}';
    }
}

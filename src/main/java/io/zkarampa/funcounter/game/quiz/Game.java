package io.zkarampa.funcounter.game.quiz;

import io.zkarampa.funcounter.Player;
import io.zkarampa.funcounter.game.quiz.Quiz;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players = new ArrayList<>();
    private Quiz quiz;

    public Game(Quiz quiz) {
        this.quiz = quiz;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", quiz=" + quiz +
                '}';
    }
}

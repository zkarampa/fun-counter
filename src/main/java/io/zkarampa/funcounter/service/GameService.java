package io.zkarampa.funcounter.service;

import io.zkarampa.funcounter.game.quiz.Game;
import io.zkarampa.funcounter.game.quiz.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
@Slf4j
public class GameService {

    static Map<Integer, Game> games = new HashMap<>();

    public int createGame(int nQuestions, int nAnswers) {
        Quiz quiz = new Quiz();

        IntStream.range(0, nQuestions)
                .mapToObj(i -> new Quiz.Entry(
                        "How many windows does TARDIS has?",
                        "4D",
                        "None", "A2", "B3", "C4"
                ))
                .forEach(quiz::addQuestion);

        int gameId;
        do {
            gameId = ThreadLocalRandom.current().nextInt(100_000, 1_000_000);
            log.info("New gameId = {}", gameId);
        } while (games.containsKey(gameId));

        games.put(gameId, new Game(quiz));
        return gameId;
    }

    public Game getGame(int key) {
        return games.get(key);
    }

    public String getInfo(int key) {
        return getGame(key).toString();
    }

}

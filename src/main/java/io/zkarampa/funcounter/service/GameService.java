package io.zkarampa.funcounter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zkarampa.funcounter.game.quiz.Game;
import io.zkarampa.funcounter.game.quiz.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
@Slf4j
public class GameService {

    static Map<Integer, Game> games = new HashMap<>();

    private final ObjectMapper objectMapper;

    public GameService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public int createGame(String rawJSON) throws JsonProcessingException {
        Quiz quiz = new Quiz();

        objectMapper.readValue(rawJSON,
                new TypeReference<List<Quiz.Entry>>() {
                })
                .forEach(quiz::addQuestion);

        int gameId = createGameId();
        games.put(gameId, new Game(quiz));
        return gameId;

    }

    private int createGameId() {
        int gameId;
        do {
            gameId = ThreadLocalRandom.current().nextInt(100_000, 1_000_000);
            log.info("New gameId = {}", gameId);
        } while (games.containsKey(gameId));
        return gameId;
    }

    public Game getGame(int key) {
        return games.get(key);
    }

    public String getInfo(int key) {
        return getGame(key).toString();
    }

}

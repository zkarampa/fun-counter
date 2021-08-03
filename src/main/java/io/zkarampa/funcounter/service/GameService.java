package io.zkarampa.funcounter.service;

import io.zkarampa.funcounter.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class GameService {

    static Map<String, Game> games = new HashMap<>();


    public String createGame(Game input){
        int id = new Random().nextInt(999999);
        String key = String.format("%06d", id);


        games.put(key, input);

        return key;

    }


    public Game getGame(String key) {
        return games.get(key);
    }


    public String getInfo(String key){
        return getGame(key).toString();
    }






}

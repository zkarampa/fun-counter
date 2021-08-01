package io.zkarampa.funcounter.service;

import io.zkarampa.funcounter.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {

    static Map<String, Game> games = new HashMap<>();


    public String createGame(Game input){

        String key = UUID.randomUUID().toString();

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

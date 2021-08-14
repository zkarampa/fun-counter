package io.zkarampa.funcounter.web.controller;

import io.zkarampa.funcounter.game.quiz.Game;
import io.zkarampa.funcounter.Player;
import io.zkarampa.funcounter.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/admin")
    public String index(Model model, HttpSession session) {
        Integer gameId = (Integer) session.getAttribute("GAME_ID");
        log.info("Admin - GameId: {}", gameId);
        model.addAttribute("gameId", gameId);
        return "admin";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return "redirect:/game/admin";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("nQuestions") int nQuestions,
            @RequestParam("nAnswers") int nAnswers,
            HttpServletRequest request) {
        log.info("Create - Game Details: {}, {}", nQuestions, nAnswers);
        Integer gameId = gameService.createGame(nQuestions, nAnswers);
        log.info("Create - Game created: {}", gameId);
        request.getSession().setAttribute("GAME_ID", gameId);
        return "redirect:/game/admin";
    }

    @PostMapping("/start")
    public String start(
            @RequestParam("nQuestions") int nQuestions,
            @RequestParam("nAnswers") int nAnswers,
            HttpServletRequest request) {
        log.info("Info: {}, {}", nQuestions, nAnswers);
       return "redirect:/game/admin";
    }

//================================================
    @GetMapping("/join")
    public String join(@RequestParam(name="gameId", required=true) Integer gameId, Model model, HttpSession session) {
        log.info("Join - GameId: {}", gameId);

        String playerName = (String) session.getAttribute("PLAYER_NAME");
        if (playerName == null){
            model.addAttribute("redirectUrl", String.format("redirect:/game/join?gameId=%s", gameId));
            return "register";
        }

        Game game = gameService.getGame(gameId);
        game.addPlayer(new Player(playerName));

        return String.format("redirect:/game/play?gameId=%s", gameId);
    }

    @PostMapping("/register")
    public String register(@RequestParam(name="name", required=true) String name,
            @RequestParam(name="redirectUrl", required=true) String redirectUrl,
                           Model model,
                           HttpSession session) {
        log.info("Register - Name: {}, RedirectUrl: {}", name, redirectUrl);

        session.setAttribute("PLAYER_NAME", name);

        return redirectUrl;
    }


    @GetMapping("/play")
    public String play(@RequestParam(name="gameId", required=true) Integer gameId, Model model, HttpSession session) {
        log.info("Play - GameId: {}", gameId);

        String playerName = (String) session.getAttribute("PLAYER_NAME");
        log.info("Play - PlayerName: {}", playerName);

        String info = gameService.getInfo(gameId);
        model.addAttribute("info", info);
        return "game";
    }

}

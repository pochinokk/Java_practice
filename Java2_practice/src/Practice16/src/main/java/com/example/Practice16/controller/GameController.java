package com.example.Practice16.controller;

import com.example.Practice16.entity.Game;
import com.example.Practice16.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {
    @Autowired
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/games")
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = gameService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @PostMapping(path = "/games")
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        try {
            gameService.create(game);
            return ResponseEntity.status(HttpStatus.CREATED).body("Game was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/games/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable("id") Long id) {
        try {
            if (gameService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Game was deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

package com.example.Practice15.controller;

import com.example.Practice15.entity.Game;
import com.example.Practice15.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping(path = "/games")
    public @ResponseBody ResponseEntity getGames() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gameService.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/games")
    public @ResponseBody ResponseEntity createGame(Game game) {
        try {
            gameService.create(game);
            return ResponseEntity.status(HttpStatus.OK).body("Game was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/games")
    public @ResponseBody ResponseEntity deleteGame(int id) {
        try {
            if (gameService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Game was deleted");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
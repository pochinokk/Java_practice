package com.example.Practice20.controller;

import com.example.Practice20.entity.Game;
import com.example.Practice20.service.GameService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {
    @Autowired
    private final GameService gameService;
    @PersistenceContext
    private EntityManager entityManager;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/games")
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = gameService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @GetMapping(path = "/sorted_games_by_date")
    public ResponseEntity<List<Game>> sort_by_creation_date(Model model, @RequestParam(required = false) String sort) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> gameCriteriaQuery = builder.createQuery(Game.class);
        Root<Game> root = gameCriteriaQuery.from(Game.class);
        gameCriteriaQuery.select(root).orderBy(builder.asc(root.get("creationDate")));
        Query query = entityManager.createQuery(gameCriteriaQuery);
        List<Game> sortedGames = query.getResultList();
        return ResponseEntity.status(HttpStatus.OK).body(sortedGames);
    }

    @GetMapping(path = "/sorted_games_by_name")
    public ResponseEntity<List<Game>> sort_by_game_name(Model model, @RequestParam(required = false) String sort) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> gameCriteriaQuery = builder.createQuery(Game.class);
        Root<Game> root = gameCriteriaQuery.from(Game.class);
        gameCriteriaQuery.select(root).orderBy(builder.asc(root.get("name")));
        Query query = entityManager.createQuery(gameCriteriaQuery);
        List<Game> sortedGames = query.getResultList();
        return ResponseEntity.status(HttpStatus.OK).body(sortedGames);
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
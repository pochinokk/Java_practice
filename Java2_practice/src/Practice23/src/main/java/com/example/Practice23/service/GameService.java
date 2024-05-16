package com.example.Practice23.service;

import com.example.Practice23.entity.Game;
import com.example.Practice23.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        log.info("Create Game repo");
        this.gameRepository = gameRepository;
    }
    @Transactional
    public void create(Game game) {
        log.info("Save the game");
        gameRepository.save(game);
    }
    @Transactional
    public List<Game> readAll() {
        log.info("Read all games");
        return gameRepository.findAll();
    }
    @Transactional
    public Game read(long id) {

        log.info("Read a game by id");
        return gameRepository.getReferenceById(id);
    }
    @Transactional
    public boolean update(Game game, long id) {
        log.info("Update");
        if (gameRepository.existsById(id)) {
            game.setId(id);
            gameRepository.save(game);
            return  true;
        }
        return false;
    }

    @Transactional
    public boolean delete(long id) {
        log.info("Delete");
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Transactional
    public List<Game> findAllByName(String name) {
        log.info("findAllByName");
        return gameRepository.findAllByName(name);
    }
}


package com.example.Practice24.service;

import com.example.Practice24.entity.Game;
import com.example.Practice24.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
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
    public Game create(Game game) throws DataAccessException {
        log.info("Save the game");
        try {
            return gameRepository.save(game);
        } catch (DataAccessException ex) {
            log.error("Error while saving game: {}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional
    public List<Game> readAll() {
        log.info("Read all games");
        return gameRepository.findAll();
    }
    @Transactional
    public Game read(long id) {
        log.info("Read game by id");
        return gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found"));
    }

    @Transactional
    public Game update(Game game, long id) {
        log.info("Update the game");
        if (gameRepository.existsById(id)) {
            try {
                game.setId(id);
                return gameRepository.save(game);
            } catch (DataAccessException ex) {
                log.error("Error while saving game: {}", ex.getMessage());
                throw ex;
            }
        } else {
            throw new IllegalArgumentException("Game with id " + id + " does not exist.");
        }
    }
    @Transactional
    public boolean delete(long id) {
        log.info("Delete the game");
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


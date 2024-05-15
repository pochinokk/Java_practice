package com.example.Practice17.service;

import com.example.Practice17.entity.Game;
import com.example.Practice17.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void create(Game game) {
        gameRepository.save(game);
    }

    public List<Game> readAll() {
        return gameRepository.findAll();
    }

    public Game read(long id) {
        return gameRepository.getReferenceById(id);
    }
    public boolean update(Game game, long id) {
        if (gameRepository.existsById(id)) {
            game.setId(id);
            gameRepository.save(game);
            return  true;
        }
        return false;
    }


    public boolean delete(long id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


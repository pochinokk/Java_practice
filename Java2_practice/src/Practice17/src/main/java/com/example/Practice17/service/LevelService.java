package com.example.Practice17.service;

import com.example.Practice17.entity.Level;
import com.example.Practice17.repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {
    private final LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }


    public void create(Level level) {
        levelRepository.save(level);
    }


    public List<Level> readAll() {
        return levelRepository.findAll();
    }


    public Level read(long id) {
        return levelRepository.getReferenceById(id);
    }

    public boolean update(Level level, long id) {
        if (levelRepository.existsById(id)) {
            level.setId(id);
            levelRepository.save(level);
            return  true;
        }

        return false;
    }
    public boolean delete(long id) {
        if (levelRepository.existsById(id)) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
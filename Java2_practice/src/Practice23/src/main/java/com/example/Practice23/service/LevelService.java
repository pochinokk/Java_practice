package com.example.Practice23.service;

import com.example.Practice23.entity.Level;
import com.example.Practice23.repository.LevelRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LevelService {
    private final LevelRepository levelRepository;


    public LevelService(LevelRepository levelRepository) {
        log.info("Create level repo");
        this.levelRepository = levelRepository;
    }

    @Transactional
    public void create(Level level) {
        log.info("Save the level");
        levelRepository.save(level);
    }


    public List<Level> readAll() {
        log.info("Find all levels");
        return levelRepository.findAll();
    }


    public Level read(long id) {
        log.info("Read level by id");
        return levelRepository.getReferenceById(id);
    }

    public boolean update(Level level, long id) {
        log.info("Update");
        if (levelRepository.existsById(id)) {
            level.setId(id);
            levelRepository.save(level);
            return  true;
        }

        return false;
    }
    public boolean delete(long id) {
        log.info("Delete");
        if (levelRepository.existsById(id)) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Level> getLevelsByComplexity(String complexity) {
        log.info("getLevelsByComplexity");
        return levelRepository.findAllByComplexity(complexity);
    }
    public List<Level> getLevelsByLevelName(String levelName) {

        log.info("getLevelsByLevelName");
        return levelRepository.findAllByLevelName(levelName);
    }
    public List<Level> findAllByComplexityAndLevelName(String complexity, String levelName) {
        log.info("findAllByComplexityAndLevelName");
        return levelRepository.findAllByComplexityAndLevelName(complexity, levelName);
    }
}
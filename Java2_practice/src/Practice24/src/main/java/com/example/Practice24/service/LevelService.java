package com.example.Practice24.service;

import com.example.Practice24.entity.Level;
import com.example.Practice24.repository.LevelRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
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
    public Level create(Level level) throws DataAccessException {
        log.info("Save the level");
        try {
            return levelRepository.save(level);
        } catch (DataAccessException ex) {
            log.error("Error while saving level: {}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional
    public List<Level> readAll() {
        log.info("Find all levels");
        return levelRepository.findAll();
    }

    @Transactional
    public Level read(long id) {
        log.info("Read level by id");
        return levelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Level with id " + id + " not found"));
    }

    @Transactional
    public Level update(Level level, long id) {
        log.info("Update");
        if (levelRepository.existsById(id)) {
            try {
                level.setId(id);
                return levelRepository.save(level);
            } catch (DataAccessException ex) {
                log.error("Error while saving level: {}", ex.getMessage());
                throw ex;
            }
        } else {
            throw new IllegalArgumentException("Level with id " + id + " does not exist.");
        }
    }

    @Transactional
    public boolean delete(long id) {
        log.info("Delete");
        if (levelRepository.existsById(id)) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
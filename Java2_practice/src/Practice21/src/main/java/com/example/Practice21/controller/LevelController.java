package com.example.Practice21.controller;

import com.example.Practice21.entity.Level;
import com.example.Practice21.service.EmailService;
import com.example.Practice21.service.LevelService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LevelController {
    @Autowired
    private final LevelService levelService;
    @Autowired
    private EmailService emailService;
    @PersistenceContext
    private EntityManager entityManager;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(path = "/levels")
    public ResponseEntity<List<Level>> getLevels() {
        List<Level> levels = levelService.readAll();
        emailService.sendSimpleMessage("Level",
                String.format("Got levels"));
        return ResponseEntity.status(HttpStatus.OK).body(levels);
    }

    @GetMapping(path = "/sorted_levels_by_name")
    public ResponseEntity<List<Level>> sort_by_level_name() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Level> levelCriteriaQuery = builder.createQuery(Level.class);
        Root<Level> root = levelCriteriaQuery.from(Level.class);
        levelCriteriaQuery.select(root).orderBy(builder.asc(root.get("levelName")));
        Query query = entityManager.createQuery(levelCriteriaQuery);
        List<Level> sortedLevels = query.getResultList();
        emailService.sendSimpleMessage("Level",
                String.format("Got sorted levels by name"));
        return ResponseEntity.status(HttpStatus.OK).body(sortedLevels);
    }

    @GetMapping(path = "/sorted_levels_by_complexity")
    public ResponseEntity<List<Level>> sort_by_complexity() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Level> levelCriteriaQuery = builder.createQuery(Level.class);
        Root<Level> root = levelCriteriaQuery.from(Level.class);
        levelCriteriaQuery.select(root).orderBy(builder.asc(root.get("complexity")));
        Query query = entityManager.createQuery(levelCriteriaQuery);
        List<Level> sortedLevels = query.getResultList();
        emailService.sendSimpleMessage("Level",
                String.format("Got sorted levels by complexity"));
        return ResponseEntity.status(HttpStatus.OK).body(sortedLevels);
    }

    @PostMapping(path = "/levels")
    public ResponseEntity<String> createLevel(@RequestBody Level level) {
        try {
            if (level == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Level data is empty");
            }
            levelService.create(level);
            emailService.sendSimpleMessage("Level",
                    String.format("Created level"));
            return ResponseEntity.status(HttpStatus.CREATED).body("Level was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/levels/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable("id") Long id) {
        try {
            if (levelService.delete(id)) {
                emailService.sendSimpleMessage("Level",
                        String.format("Delete level"));
                return ResponseEntity.status(HttpStatus.OK).body("Level was deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


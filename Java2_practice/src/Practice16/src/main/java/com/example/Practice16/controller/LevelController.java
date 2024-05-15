package com.example.Practice16.controller;

import com.example.Practice16.entity.Level;
import com.example.Practice16.service.LevelService;
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

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(path = "/levels")
    public ResponseEntity<List<Level>> getLevels() {
        List<Level> levels = levelService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(levels);
    }

    @PostMapping(path = "/levels")
    public ResponseEntity<String> createLevel(@RequestBody Level level) {
        try {
            if (level == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Level data is empty");
            }
            levelService.create(level);
            return ResponseEntity.status(HttpStatus.CREATED).body("Level was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/levels/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable("id") Long id) {
        try {
            if (levelService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Level was deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

